package uk.org.pentlandscouts.scoreboard_app.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Accessibility
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

class NavBottomBar {

    companion object {

        @Composable
        fun BottomBarNavTest(navController: NavController) {


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


                            navController.navigate(
                                route = "AddScoreView"

                               // Score(teamName = "Activities")
                            )
                            {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                            }
                        })
                }
            }
        }
    }






}



