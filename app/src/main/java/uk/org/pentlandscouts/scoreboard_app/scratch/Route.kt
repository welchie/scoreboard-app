package uk.org.pentlandscouts.scoreboard_app.scratch

sealed class Route(
    val route: String
) {
    object AppStartNavigation: Route(route = "appStartNavigation")
    object SplashScreen : Route(route = "splashScreen")
    object SignInScreen : Route(route = "signInScreen")
    object SignInSupportContactScreen : Route(route = "signInSupportContactScreen")
    object HomePageNavigation : Route(route = "homePageNavigation")
    object HomeScreen : Route(route = "homeScreen")
    object PatientScreen : Route(route = "patientScreen")
    object CalendarScreen : Route(route = "calendarScreen")
    object AlarmScreen : Route(route = "alarmScreen")
    object SupportScreen : Route(route = "supportScreen")
    object ProfileScreen : Route(route = "profileScreen")
    object TeamsDropDown : Route(route = "teamsDropdown")
}