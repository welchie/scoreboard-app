package uk.org.pentlandscouts.scoreboard_app.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope


import uk.org.pentlandscouts.scoreboard_app.theme.ScoreboardAppTheme
import kotlinx.coroutines.launch

class NavigationActivity: ComponentActivity() {


    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            // Call your suspend function here, e.g., coroutineFunction()
            data = getScoreBoardData()
        }
        setContent {
            ScoreboardAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                )
                {
                    var home by remember { mutableIntStateOf(0) }
                    var menu by remember { mutableIntStateOf(0) }
                    var refresh by remember { mutableIntStateOf(0) }

                    Scaffold(
                        topBar = {

                        TopAppBar(
                            colors = topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor =  MaterialTheme.colorScheme.primary ,
                            ),
                            title = {
                                NavTopBar.TopBarNav()
                            }
                        )
                },
                        bottomBar = {
                            BottomAppBar(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                contentColor = MaterialTheme.colorScheme.primary,
                            ) {
                                Row()
                                {

                                        Button(onClick = { home++ }) {
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
                                    Button(onClick = { refresh++ }) {
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
                        },
                        floatingActionButton = {
                            FloatingActionButton(onClick = { refresh++ }) {
                                Icon(Icons.Default.Refresh, contentDescription = "Refresh")
                            }
                        }
                    ) { innerPadding ->
                        Column(
                            modifier = Modifier
                                .padding(innerPadding),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                        ) {
                            ScoreBoardView(LocalContext.current)
                        }
                    }
                }
            }
        }
    }
}








