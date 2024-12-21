package uk.org.pentlandscouts.scoreboard_app.scratch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import com.snap40.mobile.feature_common.nav_graph.NavGraphHost
import com.snap40.mobile.feature_common.nav_graph.Route
import com.snap40.mobile.feature_main.presentation.viewmodels.MainViewModel
import com.snap40.mobile.ui.theme.MonitoringAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MonitoringAppTheme(darkTheme = false) {
                NavGraphHost(startDestination = Route.AppStartNavigation.route, )
            }
        }
    }
}