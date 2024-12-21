package uk.org.pentlandscouts.scoreboard_app.scratch

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.snap40.mobile.feature_calendar.presentation.CalendarScreen
import com.snap40.mobile.feature_main.presentation.screens.HomeScreen
import com.snap40.mobile.feature_patient.presentation.screens.PatientScreen
import com.snap40.mobile.feature_patient.presentation.viewmodels.PatientScreenViewModel
import com.snap40.mobile.feature_signin.presentation.screens.SignInScreen
import com.snap40.mobile.feature_signin.presentation.screens.SignInSupport
import com.snap40.mobile.feature_signin.presentation.screens.SplashScreen
import com.snap40.mobile.feature_signin.presentation.viewmodels.SignInViewModel

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
            composable(
                route = Route.SplashScreen.route
            ) {
                val viewModel = it.sharedViewModel<SignInViewModel>(navController)
                SplashScreen(navigate = { route ->
                    navController.navigate(route)
                })
            }

            composable(
                route = Route.SignInScreen.route
            ) {
                val viewModel = it.sharedViewModel<SignInViewModel>(navController)
                SignInScreen(navigate = { route ->
                    navController.navigate(route)
                })
            }

            composable(
                route = Route.SignInSupportContactScreen.route
            ) {
                val viewModel = it.sharedViewModel<SignInViewModel>(navController)
                SignInSupport(navigate = { route ->
                    navController.navigate(route)
                })
            }
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

            composable(route = Route.PatientScreen.route) {
                val patientScreenViewModel = it.sharedViewModel<PatientScreenViewModel>(navController)
                PatientScreen(patientScreenViewModel)
            }

            composable(route = Route.CalendarScreen.route) {
                CalendarScreen()
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