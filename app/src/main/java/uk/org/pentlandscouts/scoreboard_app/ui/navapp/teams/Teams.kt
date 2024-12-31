package uk.org.pentlandscouts.scoreboard_app.ui.navapp.teams

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import uk.org.pentlandscouts.scoreboard_app.ui.TeamsDropDown

@Composable
fun TeamsScreen()
{
    Row()
    {
        Column() {
            Text(text = "Teams")
        }
    }
}

@Preview
@Composable
fun TeamsPrev()
{
    TeamsScreen()
}
