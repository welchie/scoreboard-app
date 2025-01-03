package uk.org.pentlandscouts.scoreboard_app.ui.scoreboard

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.json.JSONArray
import uk.org.pentlandscouts.scoreboard_app.data.ActivityItem
import uk.org.pentlandscouts.scoreboard_app.ui.ScoreboardViewModel
import uk.org.pentlandscouts.scoreboard_app.ui.activities.ActivitiesDropDown

@Composable
fun AddScoreScreen(
    viewModel: ScoreboardViewModel,
    context:Context,
    teamName: String?,
    teamID: Int,
    onScoreBoard: () -> Unit = {},
) {
    var score by remember { mutableStateOf("")}
    val activitiesData: JSONArray
    var activitiesList: List<ActivityItem>
    activitiesList = ArrayList()
    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }

    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    //Build UI View of ScoreboardData from the API
    when(isLoading) {
        ScoreboardViewModel.State.Loading -> CircularProgressIndicator() // Show loading
        else -> {


            //Get Activities list here = passed to the Dropdown list
            //activitiesList = ArrayList()
            activitiesData = viewModel.activities
            for (i in 0 until activitiesData.length()) {
                val row = activitiesData.getJSONObject(i)
                activitiesList = activitiesList +
                        ActivityItem(
                            row.get("name").toString(),
                            row.get("description").toString(),
                            row.get("maxScore").toString().toInt(),
                            row.get("zone").toString(),
                            row.get("id").toString().toInt()
                        )
            }


            Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier.padding(5.dp)
            )
            {

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Transparent,
                    ),
                    modifier = Modifier.size(width = 240.dp, height = 400.dp)
                )
                {

                    Icon(Icons.Default.Add, contentDescription = "Add Score")
                    Text(text = "Team Name:$teamName")

                    viewModel.activities
                    ActivitiesDropDown(
                        activitiesList = activitiesList,
                        onItemClick = { selectedIndex = it })

                    if (activitiesList.filter{ it.id == selectedIndex}.size ==1) {
                        //val filteredList: List<ActivityItem> = activitiesList.filter{ it.ID == selectedIndex}
                        val item: ActivityItem = activitiesList.filter{ it.id == selectedIndex}[0]
                        //filteredList[0]

                        Text(
                            text = "You have chosen Activity ID: $selectedIndex ${item.name}",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(3.dp)
                                .fillMaxWidth()
                                .background(Color.LightGray),
                        )
                        OutlinedTextField(
                            value = score,
                            onValueChange = { score = it },
                            label = { Text("Score") }
                        )
                    }

                    Button(
                        onClick = {

                            // inside on click we are displaying the toast message.
                            Toast.makeText(
                                context,
                                "Add Score $teamName $score $selectedIndex $teamID",
                                Toast.LENGTH_SHORT
                            ).show()

                            viewModel.addScore(teamID,selectedIndex, score.toInt())

                            //Retrieve the scoreboard again ?

                            //MOve back to the Scoreboard
                            onScoreBoard()
                        },
                        modifier = Modifier.width(115.dp)
                    )
                    {
                        Text(text = "Add Score")
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Button(onClick = { onScoreBoard() }, modifier = Modifier.width(115.dp))
                    {
                        Text(text = "Cancel")
                    }
                }
            }
        }
    }
}

