package uk.org.pentlandscouts.scoreboard_app.scratch

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.org.pentlandscouts.scoreboard_app.theme.TabBackground
import uk.org.pentlandscouts.scoreboard_app.theme.TabHighLightBackground
import uk.org.pentlandscouts.scoreboard_app.theme.TabUnSelectedBackground
import uk.org.pentlandscouts.scoreboard_app.theme.White

@Composable
fun HomeScreenBottomNavigationBar(navigate: (String) -> Unit) {

    val items = listOf(
        HomeScreenBottomNavigationItem.Patients,
        HomeScreenBottomNavigationItem.Calendar,
        HomeScreenBottomNavigationItem.Alarms,
        HomeScreenBottomNavigationItem.Support,
        HomeScreenBottomNavigationItem.Profile
    )
    var selectedItem by remember { mutableIntStateOf(0) }
    var currentRoute by remember { mutableStateOf(Route.PatientScreen.route) }

    items.forEachIndexed { index, navigationItem ->
        if (navigationItem.route == currentRoute) {
            selectedItem = index
        }
    }

    NavigationBar(
        containerColor = TabBackground,
        contentColor = White,
    ) {
        items.forEachIndexed { index, item ->

            Box(
                modifier = Modifier.fillMaxWidth().weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                    val isSelected = index == selectedItem

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        // Top bar indicator, shown only for the selected item
                        if (isSelected) {
                            HorizontalDivider(
                                modifier = Modifier
                                    .width(54.dp),
                                thickness = 4.dp,
                                color = TabHighLightBackground,
                            )
                        }

                        // Navigation bar item
                        this@NavigationBar.NavigationBarItem(
                            alwaysShowLabel = true,
                            icon = {
                                Image(
                                    painter = painterResource(item.icon),
                                    contentDescription = stringResource(id = item.title),
                                    modifier = Modifier.size(19.dp),
                                    colorFilter = if (isSelected) {
                                        ColorFilter.tint(TabHighLightBackground)
                                    } else {
                                        ColorFilter.tint(TabUnSelectedBackground)
                                    }
                                )
                            },
                            label = {
                                Text(
                                    text = stringResource(id = item.title),
                                    fontSize = 14.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                )
                            },
                            selected = isSelected,
                            onClick = {
                                selectedItem = index
                                currentRoute = item.route
                                navigate(item.route)
                            },
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = Color.Transparent,
                                selectedIconColor = TabHighLightBackground,
                                selectedTextColor = TabHighLightBackground,
                                unselectedIconColor = TabUnSelectedBackground,
                                unselectedTextColor = TabUnSelectedBackground
                            )
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreenBottomNavigationBar() {
    HomeScreenBottomNavigationBar(navigate = {})
}