package uk.org.pentlandscouts.scoreboard_app.ui.navapp.scoreboard

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.json.JSONArray
import uk.org.pentlandscouts.scoreboard_app.data.ScoreItem
import uk.org.pentlandscouts.scoreboard_app.ui.navapp.ScoreboardViewModel
import uk.org.pentlandscouts.scoreboard_app.ui.scoreBoardList
import uk.org.pentlandscouts.scoreboard_app.util.ResourceUtils

@Composable
fun ScoreboardScreen(viewModel: ScoreboardViewModel, context: Context) {

    val data: JSONArray
    //val scoreboardViewModel = viewModel<ScoreboardViewModel>()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    //Build UI View of ScoreboardData from the API
    when(isLoading)
    {
        ScoreboardViewModel.State.Loading ->  CircularProgressIndicator() // Show loading
        else -> {
            scoreBoardList = ArrayList()
            data = viewModel.scoreboardData
            for (i in 0 until data.length()) {
                val row = data.getJSONObject(i)
                scoreBoardList = scoreBoardList +
                        ScoreItem(
                            row.get("teamName").toString(),
                            row.get("numActivities").toString().toInt(),
                            row.get("score").toString().toInt(),
                            row.get("teamLogo").toString()
                        )
            }

        }
    }



    Spacer(modifier = Modifier.height(9.dp))

    Row(
        Modifier
            .padding(5.dp)
            .background(Color.White)
            .fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Start,
    )
    {
        Icon(Icons.Default.AccountCircle, contentDescription = "Add Score")
        Text(text = "Score Board", fontWeight = FontWeight.Bold, color = Color.Black)
    }


    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        modifier = Modifier
            .padding(5.dp)
            .background(Color.White)
    ) {
        // Display Scoreboard Items
        items(scoreBoardList.size) {
            Card(
                onClick = {
                    // inside on click we are displaying the toast message.
                    Toast.makeText(
                        context,
                        "Show " + scoreBoardList[it].teamName + " team scores..",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                // on below line we are adding padding from our all sides.
                modifier = Modifier.padding(8.dp),
            ) {
                // on below line we are creating a column on below sides.
                Row(
                    modifier = Modifier.background(Color.White)
                )
                {
                    Spacer(modifier = Modifier.width(35.dp))
                    Text(
                        text = "Logo",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.width(45.dp))
                    Text(
                        text = "Team",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.width(40.dp))
                    Text(
                        text = "Score",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.width(45.dp))
                    Text(
                        text = "Add",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.width(650.dp))
                }
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

                        //Get the resourceId from MipMap
                        //var resID = ResourceUtils.getResourceIdFromMipMap(scoreBoardList[it].teamName)
                        painter = painterResource(
                            id = ResourceUtils.getResourceIdFromMipMap(
                                scoreBoardList[it].teamImg
                            )
                        ),
                        contentDescription = "Team Icon",
                        modifier = Modifier
                            .height(60.dp)
                            .width(60.dp)
                            .padding(2.dp)
                    )

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

                    Spacer(modifier = Modifier.width(10.dp))

                    OutlinedButton(
                        onClick = {
                            Toast.makeText(
                                context,
                                "Navigate to Add Score for Team: " + scoreBoardList[it].teamName,
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        contentPadding = PaddingValues(5.dp),
                    )
                    {
                        Icon(Icons.Default.Add, contentDescription = "Add Score")
                    }
                }
            }
        }
    }
}