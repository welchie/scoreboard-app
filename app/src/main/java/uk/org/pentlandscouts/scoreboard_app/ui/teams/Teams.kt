package uk.org.pentlandscouts.scoreboard_app.ui.teams

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.org.pentlandscouts.scoreboard_app.ui.ScoreboardViewModel
import uk.org.pentlandscouts.scoreboard_app.util.ResourceUtils

@Composable
fun TeamsScreen(viewModel: ScoreboardViewModel) {
    val context = LocalContext.current
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
                Text(
                    text = state.message,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = { viewModel.refresh() }) {
                    Text("Retry")
                }
            }
        }
        is ScoreboardViewModel.UiState.Success -> {
            val teamsList = state.teams

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Teams",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Logo",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.width(64.dp)
                    )
                    Text(
                        text = "Team Name",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "ID",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.End,
                        modifier = Modifier.width(48.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Edit",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.width(56.dp)
                    )
                }

                HorizontalDivider()
                Spacer(modifier = Modifier.height(8.dp))

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(teamsList, key = { it.id }) { team ->
                        Card(
                            onClick = {
                                Toast.makeText(
                                    context,
                                    "Show ${team.teamName} Team",
                                    Toast.LENGTH_SHORT
                                ).show()
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 12.dp, vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(
                                        id = ResourceUtils.getResourceIdFromMipMap(team.logo)
                                    ),
                                    contentDescription = "${team.teamName} Icon",
                                    modifier = Modifier
                                        .size(56.dp)
                                        .padding(4.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = team.teamName,
                                    style = MaterialTheme.typography.bodyLarge,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = team.id.toString(),
                                    textAlign = TextAlign.End,
                                    modifier = Modifier.width(48.dp)
                                )
                                Spacer(modifier = Modifier.width(16.dp))
                                OutlinedButton(
                                    onClick = {
                                        Toast.makeText(
                                            context,
                                            "Navigate to Edit Team: ${team.teamName}",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    },
                                    contentPadding = PaddingValues(8.dp),
                                    modifier = Modifier.width(56.dp)
                                ) {
                                    Icon(Icons.Default.Edit, contentDescription = "Edit")
                                }
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
fun TeamsPreview() {
    TeamsScreen(viewModel = viewModel())
}
