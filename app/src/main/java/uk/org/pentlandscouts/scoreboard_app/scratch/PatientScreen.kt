package uk.org.pentlandscouts.scoreboard_app.scratch

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private val TAG = "PatientScreen"

@Composable
fun PatientScreen(patientScreenViewModel: PatientScreenViewModel) {

    val sortedPatientData by patientScreenViewModel.patientSortByCategory.collectAsState()
    Log.d(TAG, "SortedPatientData $sortedPatientData")

    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(5.dp), verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {

        sortedPatientData.forEach { category ->
            if(category.patientV2DataList.isEmpty().not()) {
                item {
                    Text(
                        text = if (category.sortedName != -1) stringResource(id = category.sortedName) else "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Start,
                        color = Black
                    )
                }
            }
            items(category.patientV2DataList) { patient ->
                PatientListItem(patient = patient, patientScreenViewModel = patientScreenViewModel)
            }
        }
    }
}

@Preview
@Composable
fun PreviewPatientScreenView() {
//    PatientScreen(PatientScreenViewModel())
}
