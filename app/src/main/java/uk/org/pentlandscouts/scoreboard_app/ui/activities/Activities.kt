package uk.org.pentlandscouts.scoreboard_app.ui.activities

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.org.pentlandscouts.scoreboard_app.ui.ScoreboardViewModel

@Composable
fun ActivitiesScreen(viewModel: ScoreboardViewModel, context: Context) {
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

            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                modifier = Modifier
                    .padding(0.dp)
                    .background(Color.White)
            ) {
                items(activitiesList.size) { index ->
                    val activity = activitiesList[index]
                    Card(
                        onClick = {
                            Toast.makeText(
                                context,
                                "Show ${activity.name} `Details`..",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        modifier = Modifier
                            .padding(8.dp)
                            .border(width = 1.dp, color = Color.LightGray),
                    ) {
                        Row(
                            modifier = Modifier
                                .background(Color.White)
                                .padding(0.dp)
                                .border(width = 1.dp, color = Color.LightGray)
                        ) {
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = "Zone", fontWeight = FontWeight.Bold, color = Color.Black)
                            Spacer(modifier = Modifier.width(25.dp))
                            Text(text = "Activity Name", fontWeight = FontWeight.Bold, color = Color.Black)
                            Spacer(modifier = Modifier.width(20.dp))
                            Text(text = "Max Score", fontWeight = FontWeight.Bold, color = Color.Black)
                            Spacer(modifier = Modifier.width(20.dp))
                            Text(text = "Edit", fontWeight = FontWeight.Bold, color = Color.Black)
                            Spacer(modifier = Modifier.width(50.dp))
                        }
                        Row(
                            Modifier
                                .fillMaxSize()
                                .padding(0.dp)
                                .background(Color.White),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly,
                        ) {
                            Text(
                                text = activity.zone,
                                color = Color.DarkGray
                            )

                            Spacer(modifier = Modifier.height(9.dp))

                            Text(
                                text = activity.name,
                                modifier = Modifier.padding(4.dp),
                                color = Color.DarkGray
                            )

                            Spacer(modifier = Modifier.width(20.dp))

                            Text(
                                text = activity.maxScore.toString(),
                                color = Color.DarkGray
                            )

                            Spacer(modifier = Modifier.width(40.dp))
                            OutlinedButton(
                                onClick = {
                                    Toast.makeText(
                                        context,
                                        "Navigate to Edit Activity: ${activity.name}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                },
                                contentPadding = PaddingValues(5.dp),
                            ) {
                                Icon(Icons.Default.Edit, contentDescription = "Edit Activity")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ActivitiesScreenPrev() {
    ActivitiesScreen(viewModel = viewModel(), context = LocalContext.current)
}
