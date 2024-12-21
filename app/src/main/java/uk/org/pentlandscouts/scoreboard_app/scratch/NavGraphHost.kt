package uk.org.pentlandscouts.scoreboard_app.scratch

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.hilt.navigation.compose.hiltViewModel
import uk.org.pentlandscouts.scoreboard_app.ui.TeamsDropDown

@Composable
fun NavGraphHost(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.SplashScreen.route
        ) {
        }

        navigation(
            route = Route.HomePageNavigation.route,
            startDestination = Route.HomeScreen.route
        ) {
            composable(route = Route.HomeScreen.route) {
                val patientScreenViewModel = it.sharedViewModel<PatientScreenViewModel>(navController)
                HomeScreen(navigate = {route ->
                    navController.navigate(route)
                }, patientScreenViewModel)
            }

//            composable(route = Route.PatientScreen.route) {
//                val patientScreenViewModel = it.sharedViewModel<PatientScreenViewModel>(navController)
//                PatientScreen(patientScreenViewModel)
//            }

            composable(route = Route.TeamsDropDown.route) {
                val patientScreenViewModel = it.sharedViewModel<PatientScreenViewModel>(navController)
                TeamsDropDown.TeamsDD()
            }


        }
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel(parentEntry)
}