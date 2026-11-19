package uk.org.pentlandscouts.scoreboard_app.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable
import kotlin.reflect.KClass

@Serializable
data object ScoreboardRoute

@Serializable
data object TeamsRoute

@Serializable
data object ActivitiesRoute

@Serializable
data class AddScoreRoute(
    val teamName: String,
    val teamId: Int
)

interface ScoreboardDestination {
    val icon: ImageVector
    val label: String
    val route: Any
    val routeClass: KClass<*>
}

object Scoreboard : ScoreboardDestination {
    override val icon = Icons.Filled.Home
    override val label = "Scoreboard"
    override val route = ScoreboardRoute
    override val routeClass = ScoreboardRoute::class
}

object Teams : ScoreboardDestination {
    override val icon = Icons.Default.Person
    override val label = "Teams"
    override val route = TeamsRoute
    override val routeClass = TeamsRoute::class
}

object Activities : ScoreboardDestination {
    override val icon = Icons.Default.Accessibility
    override val label = "Activities"
    override val route = ActivitiesRoute
    override val routeClass = ActivitiesRoute::class
}

val scoreboardTabRowScreens = listOf(Scoreboard, Teams, Activities)
