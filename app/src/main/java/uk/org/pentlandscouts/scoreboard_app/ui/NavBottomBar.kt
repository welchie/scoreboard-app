package uk.org.pentlandscouts.scoreboard_app.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.scoreboard_app.R

class NavBottomBar {
    companion object {
        @Composable
        fun BottomBarNav() {
            var home by remember { mutableIntStateOf(0) }
            var menu by remember { mutableIntStateOf(0) }
            var refresh by remember { mutableIntStateOf(0)}

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
    }

}

