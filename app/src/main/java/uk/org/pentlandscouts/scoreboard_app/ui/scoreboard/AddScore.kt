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
import uk.org.pentlandscouts.scoreboard_app.ui.ScoreboardViewModel
import uk.org.pentlandscouts.scoreboard_app.ui.activities.ActivitiesDropDown

@Composable
fun AddScoreScreen(
    viewModel: ScoreboardViewModel,
    context: Context,
    teamName: String?,
    teamID: Int,
    onScoreBoard: () -> Unit = {},
) {
    var score by remember { mutableStateOf("") }
    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiState) {
        is ScoreboardViewModel.UiState.Loading -> CircularProgressIndicator()
        is ScoreboardViewModel.UiState.Error -> Text(
            text = state.message,
            color = Color.Red,
            modifier = Modifier.padding(16.dp)
        )
        is ScoreboardViewModel.UiState.Success -> {
            val activitiesList = state.activities

            Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier.padding(5.dp)
            ) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Transparent,
                    ),
                    modifier = Modifier.size(width = 240.dp, height = 400.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add Score")
                    Text(text = "Team Name:$teamName")

                    ActivitiesDropDown(
                        activitiesList = activitiesList,
                        onItemClick = { selectedIndex = it }
                    )

                    val selectedActivity = activitiesList.firstOrNull { it.id == selectedIndex }
                    if (selectedActivity != null) {
                        Text(
                            text = "You have chosen Activity ID: $selectedIndex ${selectedActivity.name}",
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
                            val parsedScore = score.toIntOrNull()
                            if (parsedScore == null) {
                                Toast.makeText(
                                    context,
                                    "Please enter a valid numeric score",
                                    Toast.LENGTH_SHORT
                                ).show()
                                return@Button
                            }
                            Toast.makeText(
                                context,
                                "Add Score $teamName $parsedScore $selectedIndex $teamID",
                                Toast.LENGTH_SHORT
                            ).show()

                            viewModel.addScore(teamID, selectedIndex, parsedScore) {
                                onScoreBoard()
                            }
                        },
                        modifier = Modifier.width(115.dp)
                    ) {
                        Text(text = "Add Score")
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Button(
                        onClick = { onScoreBoard() },
                        modifier = Modifier.width(115.dp)
                    ) {
                        Text(text = "Cancel")
                    }
                }
            }
        }
    }
}
