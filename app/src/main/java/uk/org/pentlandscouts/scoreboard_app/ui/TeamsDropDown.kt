package uk.org.pentlandscouts.scoreboard_app.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class TeamsDropDown {

    @Preview
    @Composable
    public fun TeamsDD()
    {
        val teams = arrayOf("Team 1", "Team 2", "Team 3", "Team 4", "Team 5")
        TeamsExposedDropDown()
    }



    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TeamsExposedDropDown() {
        val context = LocalContext.current
        val teams = arrayOf("Team 1", "Team 2", "Team 3", "Team 4", "Team 5")
        var expanded by remember { mutableStateOf(false) }
        var selectedText by remember { mutableStateOf(teams[0]) }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
        )
        {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = {
                    expanded = !expanded
                }
            )
            {
                TextField(
                    value = selectedText,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.menuAnchor()
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    teams.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(text = item) },
                            onClick = {
                                selectedText = item
                                expanded = false
                                Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                }
            }
        }
    }

companion object{
    @Composable
    fun TeamsDD()
    {
        TeamsDD()
    }
}


    @Preview
    @Composable
    fun TeamsDD2()
    {
        TeamsDD()
    }

}