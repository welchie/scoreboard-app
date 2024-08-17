package com.example.scoreboard_app

import android.annotation.SuppressLint
import android.content.Context
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
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.scoreboard_app.ui.theme.ScoreboardAppTheme

class GridViewActivity : ComponentActivity() {
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
        ScoreboardAppTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
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
                            "Add New Score",
                            Toast.LENGTH_SHORT
                        ).show() }) {
                            Icon(Icons.Default.Add, contentDescription = "Add")
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

// on below line we are creating grid view function for loading our grid view.
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun GridView(context: Context) {
    // on below line we are creating and initializing our array list
    lateinit var scoreBoardList: List<ScoreItem>
    scoreBoardList = ArrayList<ScoreItem>()

    // on below line we are adding data to our list.
    scoreBoardList = scoreBoardList + ScoreItem("Sharks",10,125,  R.mipmap.shark_foreground)
    scoreBoardList = scoreBoardList + ScoreItem("Elephants",8,99,  R.mipmap.elephant_foreground)
    scoreBoardList = scoreBoardList + ScoreItem("Owls",7,85,  R.mipmap.owl_foreground)
    scoreBoardList = scoreBoardList + ScoreItem("Bees",6,40,  R.mipmap.bee_foreground)
    scoreBoardList = scoreBoardList + ScoreItem("Foxes",6,35,  R.mipmap.fox_foreground)
    scoreBoardList = scoreBoardList + ScoreItem("Pigs",1,5,  R.mipmap.pig_foreground)

    scoreBoardList = scoreBoardList + ScoreItem("Lions",10,125,  R.mipmap.lion_foreground)
    scoreBoardList = scoreBoardList + ScoreItem("Rays",8,99,  R.mipmap.ray_foreground)
    scoreBoardList = scoreBoardList + ScoreItem("Fish",7,85,  R.mipmap.ray_foreground)
    scoreBoardList = scoreBoardList + ScoreItem("Pandas",6,40,  R.mipmap.panda_foreground)
    scoreBoardList = scoreBoardList + ScoreItem("Hippos",6,35,  R.mipmap.hippo_foreground)
    scoreBoardList = scoreBoardList + ScoreItem("Zebras",1,5,  R.mipmap.zebra_foreground)

    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        modifier = Modifier
            .padding(10.dp)
            .background(Color.White)
    ) {
        // on below line we are displaying our
        // items upto the size of the list.
        items(scoreBoardList.size) {
            Card(
                onClick = {
                    // inside on click we are displaying the toast message.
                    Toast.makeText(
                        context,
                        scoreBoardList[it].teamName + " selected..",
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
                        //painter = painterResource(id = R.mipmap.elephant_foreground),

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
                    // on the below line we are adding a spacer.
                    Spacer(modifier = Modifier.height(9.dp))

                    Text(
                        text = scoreBoardList[it].numActivities.toString(),
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