package uk.org.pentlandscouts.scoreboard_app.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
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
        startDestination = ScoreboardRoute,
        modifier = modifier
    ) {
        composable<ScoreboardRoute> {
            ScoreboardScreen(
                viewModel = viewModel,
                onAddScore = { teamName, teamId ->
                    navController.navigateSingleTopTo(
                        AddScoreRoute(teamName = teamName, teamId = teamId)
                    )
                }
            )
        }
        composable<TeamsRoute> {
            TeamsScreen(viewModel = viewModel)
        }
        composable<ActivitiesRoute> {
            ActivitiesScreen(viewModel = viewModel)
        }
        composable<AddScoreRoute> { navBackStackEntry ->
            val args = navBackStackEntry.toRoute<AddScoreRoute>()
            AddScoreScreen(
                viewModel = viewModel,
                teamName = args.teamName,
                teamID = args.teamId,
                onScoreBoard = {
                    navController.navigateSingleTopTo(ScoreboardRoute)
                }
            )
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: Any) =
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
