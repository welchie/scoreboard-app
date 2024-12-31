package uk.org.pentlandscouts.scoreboard_app.ui.navapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import uk.org.pentlandscouts.scoreboard_app.theme.Purple40
import uk.org.pentlandscouts.scoreboard_app.theme.ScoreboardAppTheme
import uk.org.pentlandscouts.scoreboard_app.ui.data
import uk.org.pentlandscouts.scoreboard_app.ui.getScoreBoardData
import uk.org.pentlandscouts.scoreboard_app.ui.navapp.components.ScoreboardTabRow
import uk.org.pentlandscouts.scoreboard_app.ui.navapp.topbar.TopBarScreen

class ScoreboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Load data in the background
        //Run Sync task here to load the data
        //new LoadDataTask().execute();

        setContent {
            ScoreboardApp()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScoreboardApp()
{
    ScoreboardAppTheme  {
        val navController = rememberNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination
        val currentScreen =
            scoreboardTabRowScreens.find { it.route == currentDestination?.route } ?: Scoreboard
        var refresh = 0
        val scoreboardViewModel = viewModel<ScoreboardViewModel>()

        Scaffold(
            topBar = {
                TopAppBar(
                    colors = topAppBarColors(
                        containerColor = Purple40,

                        //MaterialTheme.colorScheme.,
                        titleContentColor =  MaterialTheme.colorScheme.primary ,
                    ),
                    title = {
                        TopBarScreen()
                    }
                )

            },
            bottomBar = {
                ScoreboardTabRow(
                    allScreens = scoreboardTabRowScreens,
                    onTabSelected = { newScreen ->
                        navController.navigateSingleTopTo(newScreen.route)
                    },
                    currentScreen = currentScreen
                )

            }

        ) { innerPadding ->
                ScorboardNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding),
                    viewModel = scoreboardViewModel

            )
        }
    }
}
