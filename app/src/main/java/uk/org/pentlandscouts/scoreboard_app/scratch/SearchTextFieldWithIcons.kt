package uk.org.pentlandscouts.scoreboard_app.scratch

import CustomBasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.scoreboard_app.R
import uk.org.pentlandscouts.scoreboard_app.theme.White

@Composable
fun SearchTextFieldWithIcons(modifier: Modifier, patientScreenViewModel: PatientScreenViewModel) {

    val searchQueryText by patientScreenViewModel.patientSearchText.collectAsState()

    CustomBasicTextField(
        value = searchQueryText,
        onValueChange = patientScreenViewModel::updateSearchFilter,
        placeholder = stringResource(id = R.string.search_hint),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search Icon",
                tint = White
            )
        },
        trailingIcon = {
            IconButton(onClick = {
                patientScreenViewModel.updateSearchFilter("")
            }) {
                Icon(
                    Icons.Filled.Clear,
                    contentDescription = "Clear Text",
                    tint = White
                )
            }
        },
        modifier = modifier
    )
}