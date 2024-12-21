package uk.org.pentlandscouts.scoreboard_app.scratch

import com.example.scoreboard_app.R


sealed class HomeScreenBottomNavigationItem(var route: String, val icon: Int, var title: Int) {
    data object Patients : HomeScreenBottomNavigationItem(Route.PatientScreen.route, R.drawable.tab_home, R.string.patients)
    data object Calendar : HomeScreenBottomNavigationItem(Route.CalendarScreen.route, R.drawable.tab_calendar, R.string.calendar)
    data object Alarms : HomeScreenBottomNavigationItem(Route.AlarmScreen.route, R.drawable.alarm, R.string.alarms)
    data object Support : HomeScreenBottomNavigationItem(Route.SupportScreen.route, R.drawable.tab_support, R.string.support)
    data object Profile : HomeScreenBottomNavigationItem(Route.ProfileScreen.route, R.drawable.tab_patient, R.string.profile)
}