package uk.org.pentlandscouts.scoreboard_app.ui.scoreboard

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
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
    var score by rememberSaveable { mutableStateOf("") }
    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }
    var isSubmitting by remember { mutableStateOf(false) }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiState) {
        is ScoreboardViewModel.UiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is ScoreboardViewModel.UiState.Error -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = state.message, color = MaterialTheme.colorScheme.error)
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = { viewModel.refresh() }) {
                    Text("Retry")
                }
            }
        }
        is ScoreboardViewModel.UiState.Success -> {
            val activitiesList = state.activities
            val selectedActivity = activitiesList.firstOrNull { it.id == selectedIndex }
            val parsedScore = score.toIntOrNull()
            val isScoreOutOfRange = selectedActivity != null &&
                parsedScore != null &&
                parsedScore !in 0..selectedActivity.maxScore
            val isFormValid = selectedActivity != null &&
                parsedScore != null &&
                parsedScore in 0..selectedActivity.maxScore &&
                !isSubmitting

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Add Score for ${teamName ?: "Team"}",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        ActivitiesDropDown(
                            activitiesList = activitiesList,
                            onItemClick = { selectedIndex = it }
                        )

                        if (selectedActivity != null) {
                            Text(
                                text = "Activity: ${selectedActivity.name} (Zone: ${selectedActivity.zone})",
                                style = MaterialTheme.typography.bodyMedium
                            )

                            OutlinedTextField(
                                value = score,
                                onValueChange = { score = it.filter { ch -> ch.isDigit() } },
                                label = { Text("Score (0 - ${selectedActivity.maxScore})") },
                                singleLine = true,
                                isError = isScoreOutOfRange,
                                supportingText = {
                                    if (isScoreOutOfRange) {
                                        Text("Score must be between 0 and ${selectedActivity.maxScore}")
                                    } else {
                                        Text("Max score: ${selectedActivity.maxScore}")
                                    }
                                },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Button(
                                enabled = isFormValid,
                                onClick = {
                                    val validScore = parsedScore ?: return@Button
                                    isSubmitting = true
                                    viewModel.addScore(teamID, selectedIndex, validScore) { succeeded ->
                                        isSubmitting = false
                                        val msg = if (succeeded) {
                                            "Added $validScore points for $teamName"
                                        } else {
                                            "Failed to add score for $teamName"
                                        }
                                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                                        if (succeeded) {
                                            onScoreBoard()
                                        }
                                    }
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(text = "Add Score")
                            }

                            OutlinedButton(
                                onClick = { onScoreBoard() },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(text = "Cancel")
                            }
                        }
                    }
                }
            }
        }
    }
}
