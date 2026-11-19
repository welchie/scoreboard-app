package uk.org.pentlandscouts.scoreboard_app.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import uk.org.pentlandscouts.scoreboard_app.ui.ScoreboardDestination

@Composable
fun ScoreboardTabRow(
    allScreens: List<ScoreboardDestination>,
    onTabSelected: (ScoreboardDestination) -> Unit,
    currentScreen: ScoreboardDestination
) {
    NavigationBar {
        allScreens.forEach { screen ->
            NavigationBarItem(
                selected = currentScreen == screen,
                onClick = { onTabSelected(screen) },
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = screen.label
                    )
                },
                label = {
                    Text(text = screen.label)
                }
            )
        }
    }
}
