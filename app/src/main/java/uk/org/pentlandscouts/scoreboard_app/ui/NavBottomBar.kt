package uk.org.pentlandscouts.scoreboard_app.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController

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
        fun BottomBarNav2() {
            val selectedIndex = remember { mutableIntStateOf(0) }
            Box {
                BottomNavigation(elevation = 10.dp) {
                    BottomNavigationItem(icon = {
                        Icon(imageVector = Icons.Default.Home, "")
                    },
                        label = { Text(text = "Home") },
                        selected = (selectedIndex.value == 0),
                        onClick = {
                            selectedIndex.intValue = 0
                        })
                    BottomNavigationItem(icon = {
                        Icon(imageVector = Icons.Default.Person, "")
                    },
                        label = { Text(text = "Teams") },
                        selected = (selectedIndex.value == 1),
                        onClick = {
                            selectedIndex.intValue = 1

                        })
                    BottomNavigationItem(icon = {
                        Icon(imageVector = Icons.Default.Accessibility, "")
                    },
                        label = { Text(text = "Activities") },
                        selected = (selectedIndex.value == 2),
                        onClick = {
                            selectedIndex.intValue = 2
                        })
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



