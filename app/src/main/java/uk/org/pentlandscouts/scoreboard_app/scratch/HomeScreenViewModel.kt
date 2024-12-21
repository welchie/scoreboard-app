package com.snap40.mobile.feature_main.presentation.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class HomeScreenViewModel : ViewModel() {

    val visiblePermissionDialog = mutableStateListOf<String>()

    fun dismissDialog() {
        if(visiblePermissionDialog.size >= 1) {
            visiblePermissionDialog.removeAt(0)
        }
    }

    fun onPermissionResult(permission: String, isGranted : Boolean) {
        if(!isGranted && !visiblePermissionDialog.contains(permission)) {
            visiblePermissionDialog.add(permission)
        }
    }
}