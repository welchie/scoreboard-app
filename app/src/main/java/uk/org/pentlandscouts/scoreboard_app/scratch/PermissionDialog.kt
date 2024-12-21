package uk.org.pentlandscouts.scoreboard_app.scratch

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.scoreboard_app.R

import kotlin.reflect.KFunction0

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PermissionDialog(
    permissionTextProvider: PostNotificationTextProvider,
    isPermanentlyDeclined: Boolean,
    onDismiss: () -> Unit,
    onGrantButtonClicked: () -> Unit,
    onGoToAppSettingButtonClicked: KFunction0<Unit>,
    modifier: Modifier = Modifier
) {
   AlertDialog(
       onDismissRequest = onDismiss,
       confirmButton = {
           Column(
               modifier = modifier.fillMaxWidth()
           ) {
               HorizontalDivider()
               Text(
                 text = if(isPermanentlyDeclined) {
                     stringResource(id = R.string.grant_permission)
                 } else {
                     stringResource(id = R.string.ok)
                 },
                   fontWeight = FontWeight.Bold,
                   textAlign = TextAlign.Center,
                   modifier = Modifier
                       .fillMaxWidth()
                       .clickable {
                           if (isPermanentlyDeclined) {
                               onGoToAppSettingButtonClicked()
                           } else {
                               onGrantButtonClicked()
                           }
                       }
                       .padding(top = 20.dp)

               )
           }
       },
       title = {
           Text(text = stringResource(id = R.string.permission_required))
       },
       text = {
            Text(text = permissionTextProvider.getDescription(isPermanentlyDeclined))
       },
       modifier = modifier
   )
}