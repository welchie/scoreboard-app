package uk.org.pentlandscouts.scoreboard_app.scratch

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.snap40.mobile.R
import com.snap40.mobile.feature_alarms.presentation.AlarmScreen
import com.snap40.mobile.feature_calendar.presentation.CalendarScreen
import com.snap40.mobile.feature_common.nav_graph.Route
import com.snap40.mobile.feature_main.data.repository.PostNotificationTextProviderImpl
import com.snap40.mobile.feature_main.presentation.PermissionDialog
import com.snap40.mobile.feature_main.presentation.viewmodels.HomeScreenViewModel
import com.snap40.mobile.feature_patient.presentation.screens.PatientScreen
import com.snap40.mobile.feature_patient.presentation.viewmodels.PatientScreenViewModel
import com.snap40.mobile.feature_profile.presentation.screens.ProfileScreen
import com.snap40.mobile.feature_signin.presentation.screens.SignInSupport
import com.snap40.mobile.ui.theme.Black
import com.snap40.mobile.ui.theme.ScreenBackground
import com.snap40.mobile.ui.theme.TabBackground

@Composable
fun HomeScreen(navigate: (String) -> Unit, patientScreenViewModel: PatientScreenViewModel) {

    var currentScreen by remember { mutableStateOf(Route.PatientScreen.route) }
    val homeScreenViewModel = viewModel<HomeScreenViewModel>()
    val dialog = homeScreenViewModel.visiblePermissionDialog
    val context = LocalContext.current

    val notificationPermissionResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                homeScreenViewModel.onPermissionResult(
                    permission = Manifest.permission.POST_NOTIFICATIONS, isGranted = isGranted
                )
            }
        })

    Box(
        modifier = Modifier
            .background(color = Black)
            .windowInsetsPadding(WindowInsets.statusBars)
    ) {

        Scaffold(
            topBar = {
                Row(
                    modifier = Modifier
                        .background(TabBackground)
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(45.dp)
                ) {
                    SearchTextFieldWithIcons(
                        modifier = Modifier
                            .fillMaxWidth(0.9f),
                        patientScreenViewModel = patientScreenViewModel
                    )
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 5.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.icon_filter),
                            contentDescription = "filter",
                            modifier = Modifier.padding(1.dp)
                        )
                    }
                }
            },
            bottomBar = {
                Column(
                    verticalArrangement = Arrangement.Bottom
                ) {
                    BottomAppBar(
                        containerColor = TabBackground,
                        modifier = Modifier.height(110.dp)
                    ) {
                        HomeScreenBottomNavigationBar { route ->
                            currentScreen = route
                        }
                    }
                }
            },
            content = { padding ->
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(padding)
                        .background(ScreenBackground)
                ) {
                    val lifecycleOwner = LocalLifecycleOwner.current
                    DisposableEffect(key1 = lifecycleOwner, effect = {
                        val observer = LifecycleEventObserver { _, event ->
                            if (event == Lifecycle.Event.ON_START) {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//                                    notificationPermissionResultLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                                }
                            }
                        }
                        lifecycleOwner.lifecycle.addObserver(observer)
                        onDispose {
                            lifecycleOwner.lifecycle.removeObserver(observer)
                        }
                    })

                    dialog.reversed().forEach { permission ->
                        PermissionDialog(
                            permissionTextProvider = when (permission) {
                                Manifest.permission.POST_NOTIFICATIONS -> PostNotificationTextProviderImpl(
                                    context
                                )

                                else -> return@forEach
                            },
                            isPermanentlyDeclined = !ActivityCompat.shouldShowRequestPermissionRationale(
                                context as Activity, permission
                            ),
                            onDismiss = homeScreenViewModel::dismissDialog,
                            onGrantButtonClicked = {
                                homeScreenViewModel.dismissDialog()
                            },
                            onGoToAppSettingButtonClicked = context::openAppSettings
                        )
                    }

                    when (currentScreen) {
                        Route.PatientScreen.route -> PatientScreen(patientScreenViewModel)
                        Route.CalendarScreen.route -> CalendarScreen()
                        Route.AlarmScreen.route -> AlarmScreen()
                        Route.SupportScreen.route -> SignInSupport(navigate)
                        Route.ProfileScreen.route -> ProfileScreen()
                    }
                }
            }
        )
    }
}

fun Activity.openAppSettings() {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", packageName, null)
    ).also(::startActivity)
}


@Preview
@Composable
fun HomeScreenPreview() {
//    val patientRepository = PatientRepository()
//    val patientsUseCase = GetPatientsUseCase()
//    HomeScreen(navigate = {}, PatientScreenViewModel(patientsUseCase))
}