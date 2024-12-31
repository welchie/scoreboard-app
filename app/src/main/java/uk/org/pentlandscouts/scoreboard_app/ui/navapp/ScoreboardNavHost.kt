package uk.org.pentlandscouts.scoreboard_app.ui.navapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import uk.org.pentlandscouts.scoreboard_app.ui.navapp.activities.ActivitiesScreen
import uk.org.pentlandscouts.scoreboard_app.ui.navapp.scoreboard.ScoreboardScreen
import uk.org.pentlandscouts.scoreboard_app.ui.navapp.teams.TeamsScreen

@Composable
fun ScorboardNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Scoreboard.route,
        modifier = modifier
    ) {
        composable(
            route = Scoreboard.route,
            ) {
            ScoreboardScreen(context = LocalContext.current)
        }
        composable(route = Teams.route) {
            TeamsScreen()
        }
        composable(route = Activities.route) {
            ActivitiesScreen()
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }
