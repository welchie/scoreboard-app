package uk.org.pentlandscouts.scoreboard_app.ui

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat.recreate
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink

class NavBottomBar {
    
    companion object {
        @Composable
        fun BottomBarNav() {
                //var home by remember { mutableIntStateOf(0) }
            //var selectedContent by remember { mutableStateOf("AddScore") }
            var refresh by remember { mutableIntStateOf(0)}

            val navController = rememberNavController()

            Row()
            {

                Button(onClick = {
                    ScoreBoardActivity.setContent("ScoreBoard")
                }) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    )
                    {
                        Icon(
                            Icons.Default.Home,
                            contentDescription = "Home"
                        )
                        Text(text = "Home")
                    }
                }
                Button(onClick = {
                    ScoreBoardActivity.setContent("AddScore")
                }) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    )
                    {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = "Teams"
                        )
                        Text(text = "Teams")
                    }
                }
                Button(onClick = { refresh++ }) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    )
                    {
                        Icon(
                            Icons.Default.Place,
                            contentDescription = "Activities"
                        )
                        Text(text = "Activities")
                    }
                }
            }
            

        }

        @Composable
        private fun navigateToScoreView() {
            val navController = rememberNavController()
            navController.navigate(AddScoreView(context = LocalContext.current))
        }
    }

}



