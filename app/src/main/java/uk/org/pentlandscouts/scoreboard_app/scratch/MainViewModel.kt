package com.snap40.mobile.feature_main.presentation.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    init {
//        appEntryUseCases.readLoginStatus().onEach { startHomeScreen ->
//            if(startHomeScreen) {
//                _startDestination.value = Route.HomePageNavigation.route
//            } else {
////                _startDestination.value = Route.HomePageNavigation.route
//                _startDestination.value = Route.AppStartNavigation.route
//            }
//            _splashCondition.value = false
//        }.launchIn(viewModelScope)
    }
}