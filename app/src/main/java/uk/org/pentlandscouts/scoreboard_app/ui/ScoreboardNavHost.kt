package uk.org.pentlandscouts.scoreboard_app.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import uk.org.pentlandscouts.scoreboard_app.ui.activities.ActivitiesScreen
import uk.org.pentlandscouts.scoreboard_app.ui.scoreboard.AddScoreScreen
import uk.org.pentlandscouts.scoreboard_app.ui.scoreboard.ScoreboardScreen
import uk.org.pentlandscouts.scoreboard_app.ui.teams.TeamsScreen

@Composable
fun ScoreboardNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: ScoreboardViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Scoreboard.route,
        modifier = modifier
    ) {
        composable(
            route = Scoreboard.route,
            ) {
            ScoreboardScreen(viewModel = viewModel,
                        context = LocalContext.current,
            onAddScore = { s: String, i: String ->
                navController.navigateToAddAScore(teamName = s,teamID= i)
            },)

        }
        composable(route = Teams.route) {
            TeamsScreen(viewModel = viewModel, context = LocalContext.current)
        }
        composable(route = Activities.route) {
            ActivitiesScreen(viewModel = viewModel, context = LocalContext.current)
        }
        composable(
            route=AddScore.routeWithArgs,
            arguments = AddScore.arguments
        ) { navBackStackEntry ->
            val teamName =
                navBackStackEntry.arguments?.getString(AddScore.TEAMNAMEARG)

            val teamID =
                navBackStackEntry.arguments?.getString(AddScore.TEAMIDARG)

            if (teamID != null) {
                AddScoreScreen(viewModel, context = LocalContext.current, teamName = teamName, teamID = teamID.toInt(),
                    onScoreBoard = {
                        navController.navigateSingleTopTo(Scoreboard.route)
                    }
                )
            }
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
private fun NavHostController.navigateToAddAScore(teamName: String, teamID: String) {
    this.navigateSingleTopTo("${AddScore.route}/$teamName/$teamID")
}