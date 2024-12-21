package uk.org.pentlandscouts.scoreboard_app.scratch

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import uk.org.pentlandscouts.scoreboard_app.theme.GreyOffset

@Composable
fun PatientListItem(patient: PatientV2Data, patientScreenViewModel: PatientScreenViewModel) {

    Card(
        modifier = Modifier
            .background(
                patientScreenViewModel.getPatientRiskColor(),
                RoundedCornerShape(12.dp)
            )
            .padding(start = 4.dp),
        onClick = {
            /**
             * Below logic only to check api calls.
             */
            patientScreenViewModel.getPatientDataById(patient.patientId)
            patientScreenViewModel.getPatientsVitalInfo()
            patientScreenViewModel.getAlarmConditionById()
        }
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
        ) {

            //PatientHeaderLayout(patient)

            HorizontalDivider(
                color = GreyOffset, modifier = Modifier
                    .fillMaxWidth()
                    .height(0.2.dp)
                    .padding(start = 10.dp, end = 10.dp)
            )

            //PatientVitalInfoLayout(patient, patientScreenViewModel)

            HorizontalDivider(
                color = GreyOffset, modifier = Modifier
                    .fillMaxWidth()
                    .height(0.2.dp)
                    .padding(start = 10.dp, end = 10.dp)
            )

            //PatientCovidInfoLayout(patient)
        }
    }
}