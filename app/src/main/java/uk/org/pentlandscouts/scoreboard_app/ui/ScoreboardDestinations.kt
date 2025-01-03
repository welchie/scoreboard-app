package uk.org.pentlandscouts.scoreboard_app.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument


interface ScoreboardDestination {
    val icon: ImageVector
    val route: String
}

object Scoreboard : ScoreboardDestination {
    override val icon = Icons.Filled.Home
    override val route = "scoreboard"
}

object Teams : ScoreboardDestination {
    override val icon = Icons.Default.Person
    override val route = "teams"
}

object Activities : ScoreboardDestination {
    override val icon = Icons.Default.Accessibility
    override val route = "activities"
}

object AddScore: ScoreboardDestination
{
    override val icon = Icons.Default.Add
    override val route = "addScore"
    const val TEAMNAMEARG = "teamName"
    const val TEAMIDARG = "teamID"
    val routeWithArgs = "${route}/{${TEAMNAMEARG}}/{${TEAMIDARG}}"
    val arguments = listOf(
        navArgument(TEAMNAMEARG) { type = NavType.StringType },
        navArgument(TEAMIDARG) { type = NavType.StringType }
    )
}

// Screens to be displayed in the top ScoreboardTabRow
val scoreboardTabRowScreens = listOf(Scoreboard, Teams, Activities)

