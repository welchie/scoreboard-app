package uk.org.pentlandscouts.scoreboard_app.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import uk.org.pentlandscouts.scoreboard_app.data.ScoreBoardAPI.Companion.getActivities
import uk.org.pentlandscouts.scoreboard_app.data.ScoreBoardAPI.Companion.getTeams
import uk.org.pentlandscouts.scoreboard_app.theme.ScoreboardAppTheme


var teamsData : JSONArray = JSONArray()
var activitiesData : JSONArray = JSONArray()
var teamSelected:String = ""
var activitiySelected: String = ""
var scoreEntered:String = ""

class AddScoreActivity : ComponentActivity() {

    override fun onStart() {
        super.onStart()
       lifecycleScope.launch {
            teamsData = getTeamsData()
            activitiesData = getActivityData()
        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                                NavTopBar.TopBarNav()
                            }
                        )
                    },
            bottomBar = {
                BottomAppBar(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.primary ,
                ) {
                    NavBottomBar.BottomBarNav()
                }
            },

                )

                { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .background(Color.White),
                    ) {
                        AddScoreView(LocalContext.current)
                    }
                }

            }
        }
    }
    }
}

suspend fun getTeamsData() :JSONArray{
    delay(1000) // simulate a long-running operation
    //Build Score Data here
    return withContext(Dispatchers.IO) {
        getTeams()
    }
}

suspend fun getActivityData() :JSONArray{
    delay(1000) // simulate a long-running operation
    //Build Score Data here
    return withContext(Dispatchers.IO) {
        getActivities()
    }
}



// on below line we are creating grid view function for loading our grid view.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScoreView(context: Context) {
    //Build UI View of ScoreboardData from the API

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Absolute.SpaceEvenly,
    )
    {

        Column()
        {
            ExposedTeamDropDown()
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Absolute.SpaceEvenly
    )
    {

        Column()
        {
            ExposedActivitiesDropDown()
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Absolute.SpaceEvenly,
    )
    {

        Column()
        {
            ScoreTextField()
        }

    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Absolute.SpaceEvenly,
    )
    {

        Column()
        {
            Button(onClick = {
                // inside on click we are displaying the toast message.
                Toast.makeText(
                    context,
                    "Add Score Team: $teamSelected Activity: $activitiySelected Score: $scoreEntered",
                    Toast.LENGTH_SHORT
                ).show()
            }) {
                Text(text = "Add")
            }


        }

    }



}

fun finish() {
    TODO("Not yet implemented")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExposedTeamDropDown() {

    val context = LocalContext.current
    val teams: ArrayList<String> = ArrayList()
    for (i in 0 until teamsData.length()) {
        val row = teamsData.getJSONObject(i)
        teams.add(row.get("name").toString())
    }

    teams.add("")
    teams.sortByDescending { list -> list.length }

    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(teams[0]) }


    Box(
        modifier = Modifier
            .padding(10.dp)
    )
    {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        )
        {
            TextField(
                label = {
                    Text("Teams")
                },
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(

                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                teams.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedText = item
                            expanded = false
                            teamSelected = selectedText
                            //Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExposedActivitiesDropDown() {
    val context = LocalContext.current
    val activities: ArrayList<String> = ArrayList()
    for (i in 0 until activitiesData.length()) {
        val row = activitiesData.getJSONObject(i)
        activities.add(row.get("name").toString())
    }

    activities.add("")
    activities.sortByDescending { list -> list.length }

    var expandedActs by remember { mutableStateOf(false) }
    var selectedActsText by remember { mutableStateOf(activities[0]) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp)
    )
    {
        ExposedDropdownMenuBox(
            expanded = expandedActs,
            onExpandedChange = {
                expandedActs = !expandedActs
            }
        )
        {
            TextField(
                label = {
                    Text("Activities")
                },
                value = selectedActsText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedActs) },
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = expandedActs,
                onDismissRequest = { expandedActs = false }
            ) {
                activities.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedActsText = item
                            expandedActs = false
                            activitiySelected = selectedActsText
                            //Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ScoreTextField() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    TextField(
        label = {
            Text("Enter a score")
        },
        value = text,
        onValueChange = { score ->
            text = score
            scoreEntered = text.text.toString()
        }
    )
}






