package com.example.scoreboard_app

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.scoreboard_app.data.ScoreBoardAPI.Companion.getScoreBoardData
import com.example.scoreboard_app.data.ScoreItem
import com.example.scoreboard_app.ui.theme.ScoreboardAppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray


var data : JSONArray = JSONArray()
var scoreBoardList: List<ScoreItem> = ArrayList<ScoreItem>()

class GridViewActivity : ComponentActivity() {

    override fun onStart() {
        super.onStart()
       lifecycleScope.launch {
            data = getBackEndData()
        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lateinit var data: JSONArray
        lifecycleScope.launch {
        // Call your suspend function here, e.g., coroutineFunction()
        data = getBackEndData()
    }
    setContent {
        ScoreboardAppTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            )
            {
                var presses by remember { mutableIntStateOf(0) }
                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor =  MaterialTheme.colorScheme.primary ,
                            ),
                            title = {
                                Row {

                                    Image(
                                        // on below line we are specifying the drawable image for our image.
                                        painter = painterResource(id =R.mipmap.scout_logo_foreground),
                                        //painter = painterResource(id = R.mipmap.elephant_foreground),

                                        contentDescription = "Scouts icon",
                                        modifier = Modifier
                                            .height(60.dp)
                                            .width(60.dp)
                                            .padding(5.dp)
                                            .background(Color.Transparent)
                                    )
                                    // in the top bar we are specifying tile as a text
                                    Text(
                                        text = "Scoreboard App",
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Justify,
                                    )
                                }
                            }
                        )
                    },
            bottomBar = {
                BottomAppBar(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.primary ,
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "Pentland Scouts",
                    )
                }
            },
                    floatingActionButton = {
                        FloatingActionButton(onClick = { Toast.makeText(
                            this,
                            "Refreshing...",
                            Toast.LENGTH_SHORT
                        ).show()
                            val i = Intent(
                                this@GridViewActivity,
                                GridViewActivity::class.java
                            )
                            finish()
                            overridePendingTransition(0, 0)
                            startActivity(i)
                            overridePendingTransition(0, 0)

                        }) {
                            Icon(Icons.Default.Refresh, contentDescription = "Load")
                        }
                    }
                )

                { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .background(Color.White),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        GridView(LocalContext.current)
                    }
                }

            }
        }
    }
}
}

suspend fun getBackEndData() :JSONArray{
    delay(1000) // simulate a long-running operation
    //Build Score Data here
    return withContext(Dispatchers.IO) {
        getScoreBoardData()
    }

}

// on below line we are creating grid view function for loading our grid view.
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun GridView(context: Context) {
    //Build UI View of ScoreboardData from the API
    scoreBoardList= ArrayList<ScoreItem>()

    for (i in 0 until data.length()) {
        val row = data.getJSONObject(i)
        scoreBoardList = scoreBoardList +
                ScoreItem(  row.get("teamName").toString(),
                            row.get("numActivities").toString().toInt(),
                            row.get("score").toString().toInt(),
                            row.get("teamLogo").toString().toInt())
    }

    Spacer(modifier = Modifier.height(9.dp))

    Row (
        Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .background(Color.White),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceEvenly,
        )
    {
        Text(text = "Logo", fontWeight = FontWeight.Bold, color = Color.Black)
        Text(text = "Team Name", fontWeight = FontWeight.Bold, color = Color.Black)
        Text(text = "Score", fontWeight = FontWeight.Bold, color = Color.Black)
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        modifier = Modifier
            .padding(10.dp)
            .background(Color.White)
    ) {
        // Display Scoreboard Items
        items(scoreBoardList.size) {
            Card(
                onClick = {
                    // inside on click we are displaying the toast message.
                    Toast.makeText(
                        context,
                        scoreBoardList[it].teamName.toString() + " selected..",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                // on below line we are adding padding from our all sides.
                modifier = Modifier.padding(8.dp),
            ) {
                // on below line we are creating a column on below sides.
                Row(
                    Modifier
                        .fillMaxSize()
                        .padding(5.dp)
                        .background(Color.White),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                )
                {
                    // on below line we are creating image for our grid view item.
                    Image(
                        // on below line we are specifying the drawable image for our image.
                        painter = painterResource(id = scoreBoardList[it].teamImg),
                                    contentDescription = "Team Icon",
                                    modifier = Modifier
                                                .height(60.dp)
                                                .width(60.dp)
                                                .padding(5.dp)
                    )
                    Spacer(modifier = Modifier.height(9.dp))

                    Text(
                        text = scoreBoardList[it].teamName,
                        modifier = Modifier.padding(4.dp),
                        color = Color.DarkGray
                    )

                    Spacer(modifier = Modifier.height(9.dp))

                    Text(
                        text = scoreBoardList[it].score.toString(),
                        color = Color.DarkGray
                    )
                }
            }
        }
    }
}
