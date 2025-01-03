package uk.org.pentlandscouts.scoreboard_app.ui.teams

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.json.JSONArray
import uk.org.pentlandscouts.scoreboard_app.data.TeamItem
import uk.org.pentlandscouts.scoreboard_app.ui.ScoreboardViewModel
import uk.org.pentlandscouts.scoreboard_app.util.ResourceUtils

@Composable
fun TeamsScreen(viewModel: ScoreboardViewModel, context: Context)
{
    val teamsData: JSONArray
    var teamsList: List<TeamItem>
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()

    when(isLoading) {
        ScoreboardViewModel.State.Loading -> CircularProgressIndicator() // Show loading

        else -> {
            teamsList = ArrayList()
            teamsData = viewModel.teamsData
            for (i in 0 until teamsData.length()) {
                val row = teamsData.getJSONObject(i)
                teamsList = teamsList +
                        TeamItem(
                            row.get("name").toString(),
                            row.get("id").toString().toInt(),
                            row.get("logo").toString(),
                        )
            }

            Spacer(modifier = Modifier.height(9.dp))

            Row(
                Modifier
                    .padding(5.dp)
                    .background(Color.White)
                    .fillMaxWidth()
                   ,
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Start,
            )
            {
                Icon(Icons.Default.Person, contentDescription = "Teams")
                Text(text = "Teams", fontWeight = FontWeight.Bold, color = Color.Black)
            }

            Row()
            {
                Column()
                {
                    Text(text = "Teams")
                }
            }


            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                modifier = Modifier
                    .padding(5.dp)
                    .background(Color.White)

            ) {
                // Display Scoreboard Items
                items(teamsList.size) {


                    Card(
                        onClick = {
                            // inside on click we are displaying the toast message.
                            Toast.makeText(
                                context,
                                "Show " + teamsList[it].teamName + " Team",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        // on below line we are adding padding from our all sides.
                        modifier = Modifier.padding(8.dp),
                    ) {
                        // on below line we are creating a column on below sides.
                        Row(
                            modifier = Modifier.background(Color.White)
                                .padding(0.dp)
                                .border(width = 1.dp, color = Color.LightGray)
                        )
                        {
                            Spacer(modifier = Modifier.width(35.dp))
                            Text(
                                text = "Logo",
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.width(50.dp))
                            Text(
                                text = "Team Name",
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.width(20.dp))
                            Text(
                                text = "ID",
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )

                            Spacer(modifier = Modifier.width(40.dp))
                            Text(
                                text = "Edit",
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.width(50.dp))
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
                                        teamsList[it].logo
                                    )
                                ),
                                contentDescription = "Team Icon",
                                modifier = Modifier
                                    .height(60.dp)
                                    .width(60.dp)
                                    .padding(5.dp)
                            )
                            Spacer(modifier = Modifier.height(9.dp))

                            Text(
                                text = teamsList[it].teamName,
                                //scoreBoardList[it].teamName,
                                modifier = Modifier.padding(4.dp),
                                color = Color.DarkGray
                            )

                            Spacer(modifier = Modifier.height(9.dp))

                            Text(
                                text = teamsList[it].id.toString(),
                                color = Color.DarkGray
                            )



                            OutlinedButton(
                                onClick = {
                                    Toast.makeText(
                                        context,
                                        "Navigate to Edit  Team: " + teamsList[it].teamName,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                },
                                contentPadding = PaddingValues(5.dp),
                            )
                            {
                                Icon(Icons.Default.Edit, contentDescription = "Edit")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun TeamsPreview()
{
    TeamsScreen(viewModel = viewModel(), context = LocalContext.current)
}

