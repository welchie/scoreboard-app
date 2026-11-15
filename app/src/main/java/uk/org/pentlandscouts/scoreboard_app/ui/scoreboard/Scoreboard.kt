package uk.org.pentlandscouts.scoreboard_app.ui.scoreboard

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import uk.org.pentlandscouts.scoreboard_app.ui.ScoreboardViewModel
import uk.org.pentlandscouts.scoreboard_app.util.ResourceUtils

@Composable
fun ScoreboardScreen(
    viewModel: ScoreboardViewModel,
    context: Context,
    onAddScore: (String, String) -> Unit = { _: String, _: String -> }
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiState) {
        is ScoreboardViewModel.UiState.Loading -> CircularProgressIndicator()
        is ScoreboardViewModel.UiState.Error -> Text(
            text = state.message,
            color = Color.Red,
            modifier = Modifier.padding(16.dp)
        )
        is ScoreboardViewModel.UiState.Success -> {
            val scoreBoardList = state.scoreboard
            val teamsList = state.teams

            Spacer(modifier = Modifier.height(9.dp))

            Row(
                Modifier
                    .padding(5.dp)
                    .background(Color.White)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Start,
            ) {
                Icon(Icons.Default.AccountCircle, contentDescription = "Add Score")
                Text(text = "Score Board", fontWeight = FontWeight.Bold, color = Color.Black)
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                modifier = Modifier
                    .padding(5.dp)
                    .background(Color.White)
            ) {
                items(scoreBoardList.size) { index ->
                    val scoreItem = scoreBoardList[index]
                    Card(
                        onClick = {
                            Toast.makeText(
                                context,
                                "Show ${scoreItem.teamName} team scores..",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        modifier = Modifier
                            .padding(8.dp)
                            .border(width = 1.dp, color = Color.LightGray),
                    ) {
                        Row(
                            modifier = Modifier.background(Color.White)
                        ) {
                            Spacer(modifier = Modifier.width(35.dp))
                            Text(text = "Logo", fontWeight = FontWeight.Bold, color = Color.Black)
                            Spacer(modifier = Modifier.width(45.dp))
                            Text(text = "Team", fontWeight = FontWeight.Bold, color = Color.Black)
                            Spacer(modifier = Modifier.width(40.dp))
                            Text(text = "Score", fontWeight = FontWeight.Bold, color = Color.Black)
                            Spacer(modifier = Modifier.width(45.dp))
                            Text(text = "Add", fontWeight = FontWeight.Bold, color = Color.Black)
                            Spacer(modifier = Modifier.width(650.dp))
                        }
                        Row(
                            Modifier
                                .fillMaxSize()
                                .padding(0.dp)
                                .background(Color.White)
                                .border(width = 1.dp, color = Color.LightGray),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly,
                        ) {
                            Image(
                                painter = painterResource(
                                    id = ResourceUtils.getResourceIdFromMipMap(scoreItem.teamImg)
                                ),
                                contentDescription = "Team Icon",
                                modifier = Modifier
                                    .height(60.dp)
                                    .width(60.dp)
                                    .padding(2.dp)
                            )

                            Text(
                                text = scoreItem.teamName,
                                modifier = Modifier.padding(4.dp),
                                color = Color.DarkGray
                            )

                            Spacer(modifier = Modifier.height(9.dp))

                            Text(
                                text = scoreItem.score.toString(),
                                color = Color.DarkGray
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            OutlinedButton(
                                onClick = {
                                    val matchingTeam = teamsList.firstOrNull {
                                        it.teamName == scoreItem.teamName
                                    }
                                    if (matchingTeam != null) {
                                        onAddScore(scoreItem.teamName, matchingTeam.id.toString())
                                    }
                                },
                                contentPadding = PaddingValues(5.dp),
                            ) {
                                Icon(Icons.Default.Add, contentDescription = "Add Score")
                            }
                        }
                    }
                }
            }
        }
    }
}
