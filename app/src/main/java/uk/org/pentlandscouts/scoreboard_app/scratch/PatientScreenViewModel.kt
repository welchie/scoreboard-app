package uk.org.pentlandscouts.scoreboard_app.scratch

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/**
 * Below couple of use cases will be removed later. When actual code implementation of use case is handled.
 * If it still remains. Move below use case to data class and pass one parameter here.
 */
@HiltViewModel
class PatientScreenViewModel @Inject constructor(
    //private val getPatientAlarmWatchUseCase: GetPatientAlarmWatchUseCase,

) : ViewModel() {

    private val TAG = "PatientScreenViewModel"

    private val _patientSearchText = MutableStateFlow("")
    val patientSearchText = _patientSearchText.asStateFlow()

    private val _patientSortByCategory =
        MutableStateFlow(listOf(PatientSortByCategory(-1, emptyList())))
    val patientSortByCategory = _patientSortByCategory.asStateFlow()

    private val _patientData = MutableStateFlow(listOf<PatientV2Data>())
    val patientData = _patientData.asStateFlow()



    private val _patientWatchListCount = MutableStateFlow(0)
    val patientWatchListCount = _patientWatchListCount.asStateFlow()


    init {
        getPatientHomeScreenData()
    }

    /**
     * Method will get patient data from v2 api and call will other method to get formatted data
     */
    private fun getPatientHomeScreenData() {


    }

    /**
        Method will get sorted patient data based on filter section or default filter section.
        It'll also call ongoing alarm function to capture patient ongoing alarm data.
     */
    private fun getPatientSortByCategoryData(): List<PatientSortByCategory>? {
//        getPatientsOnGoingAlarm()
//        val watchListPatientId = _patientData.value.filter { it.watching }.map { it.patientId }
//        _patientWatchListCount.value = watchListPatientId.size
//        return getPatientsSortByUseCase(
//            patientFilterModel.value,
//            patientData.value,
//            patientOnGoingAlarmList.value
//        )
        return null;
    }

    /**
     * Method will filter listview based on user search
     */
    fun updateSearchFilter(searchFilterData: String) {
//        _patientSearchText.value = searchFilterData.trim()
//
//        _patientSearchText
//            .combine(_patientSortByCategory) { searchQuery, patientCategories ->
//
//                if (searchQuery.isBlank()) {
//                    getPatientSortByCategoryData()
//                } else {
//                    patientCategories.map { category ->
//                        category.copy(
//                            patientV2DataList = category.patientV2DataList.filter {
//                                it.matchSearchQuery(searchQuery)
//                            }
//                        )
//                    }
//                }
//            }
//            .stateIn(
//                viewModelScope,
//                SharingStarted.Lazily,
//                _patientSortByCategory.value
//            )
//            .onEach { filteredCategories ->
//                _patientSortByCategory.value = filteredCategories
//            }
//            .launchIn(viewModelScope)
    }

    fun getVitalInfoData(
        vitalInfoName: String,
        vitalValue: String?,
        vitalTimestamp: String?
    )
    {
//        return when (vitalInfoName) {
//            RESPIRATORY_RATE -> {
//                VitalsInfoUiData(
//                    vitalImage = R.drawable.resperatory_icon,
//                    vitalImageContentDescription = R.string.respiratory_rate,
//                    vitalName = R.string.vital_resp_rate,
//                    vitalNameContentDescription = R.string.respiratory_rate,
//                    vitalValue = vitalValue.toString(),
//                    vitalValueType = R.string.rpm,
//                    vitalValueTimeStamp = vitalTimestamp?.vitalCustomDateFormat() ?: ""
//                )
//            }
//
//            SPO2 -> {
//                VitalsInfoUiData(
//                    vitalImage = R.drawable.spo_icon,
//                    vitalImageContentDescription = R.string.spirometer_image,
//                    vitalName = R.string.spo2_text,
//                    vitalNameContentDescription = R.string.spirometer_image,
//                    vitalValue = vitalValue.toString(),
//                    vitalValueType = R.string.percentage,
//                    vitalValueTimeStamp = vitalTimestamp?.vitalCustomDateFormat() ?: ""
//                )
//            }
//
//            PULSE_RATE -> {
//                VitalsInfoUiData(
//                    vitalImage = R.drawable.heart_rate_icon,
//                    vitalImageContentDescription = R.string.heart_pulse_rate,
//                    vitalName = R.string.pulse_rate,
//                    vitalNameContentDescription = R.string.heart_pulse_rate,
//                    vitalValue = vitalValue.toString(),
//                    vitalValueType = R.string.bpm,
//                    vitalValueTimeStamp = vitalTimestamp?.vitalCustomDateFormat() ?: ""
//                )
//            }
//
//            COVERED_SKIN_TEMPERATURE -> {
//                VitalsInfoUiData(
//                    vitalImage = R.drawable.temperature_icon,
//                    vitalImageContentDescription = R.string.skin_temperature,
//                    vitalName = R.string.skin_temp,
//                    vitalNameContentDescription = R.string.skin_temperature,
//                    vitalValue = vitalValue.toString(),
//                    vitalValueType = R.string.fahrenheit,
//                    vitalValueTimeStamp = vitalTimestamp?.vitalCustomDateFormat() ?: ""
//                )
//            }
//
//            BLOOD_PRESSURE -> {
//                VitalsInfoUiData(
//                    vitalImage = R.drawable.bp_icon,
//                    vitalImageContentDescription = R.string.blood_pressure,
//                    vitalName = R.string.vital_bp_mm_hg,
//                    vitalNameContentDescription = R.string.blood_pressure,
//                    vitalValue = vitalValue.toString(),
//                    vitalValueType = -1,
//                    vitalValueTimeStamp = vitalTimestamp?.vitalCustomDateFormat() ?: ""
//                )
//            }
//
//            MOTION -> {
//                VitalsInfoUiData(
//                    vitalImage = R.drawable.motion_level_icon,
//                    vitalImageContentDescription = R.string.movement,
//                    vitalName = R.string.movement,
//                    vitalNameContentDescription = R.string.movement,
//                    vitalValue = vitalValue.toString(),
//                    vitalValueType = -1,
//                    vitalValueTimeStamp = vitalTimestamp?.vitalCustomDateFormat() ?: ""
//                )
//            }
//
//            else -> {
//                VitalsInfoUiData(
//                    vitalImage = R.drawable.motion_level_icon,
//                    vitalImageContentDescription = R.string.movement,
//                    vitalName = R.string.movement,
//                    vitalNameContentDescription = R.string.movement,
//                    vitalValue = vitalValue.toString(),
//                    vitalValueType = -1,
//                    vitalValueTimeStamp = ""
//                )
//            }
//        }
    }

    fun getPatientRiskColor(): Color {
//        return when (riskBand) {
//            PatientRiskBand.LOW -> PatientCardViewLow
//            PatientRiskBand.MEDIUM -> PatientCardViewMedium
//            PatientRiskBand.HIGH -> PatientCardViewCritical
//        }
        return Color.White
    }

    /**
     * Method is just for checking api calls
     */
    fun getPatientDataById(patientId: String) {
//        patientByIdUseCase(patientId)
//            .onEach { patientData ->
//                Log.d(TAG, "Patient data based on id response $patientData")
//            }
//            .launchIn(viewModelScope)
    }

    /**
     * Method is just for checking api calls
     */
    private fun getPatientsOnGoingAlarm() {
//        val patientsListId = _patientData.value.map { it.patientId }
//        getPatientAlarmWatchUseCase.patientOnGoingAlarmUseCase(patientsListId)
//            .onEach { patientData ->
//                when (patientData) {
//                    is Result.Success -> {
//                        _patientOnGoingAlarmList.value = patientData.data ?: emptyList()
//                    }
//
//                    else -> {
//                        //Do nothing
//                    }
//                }
//            }
//            .launchIn(viewModelScope)

    }

    /**
     * Method is just for checking api calls
     */
    fun getPatientsVitalInfo() {
//        val patientId = _patientData.value.map { it.patientId }
//        val facts = listOf(SPO2, MOTION, BLOOD_PRESSURE)
//        val patientsVitalInfoRequest =
//            PatientsVitalInfoRequest(patientIds = patientId, factNames = facts)
//        getPatientsVitalInfoUseCase(patientsVitalInfoRequest)
//            .onEach { vitalInfoData ->
//                Log.d(TAG, "Patient vital info response $vitalInfoData")
//            }
//            .launchIn(viewModelScope)
    }

    /**
     * Method is just for checking api calls
     */
    fun getAlarmConditionById() {
//        val alarmConditionId = "e09b5feb-4b27-42ce-9b81-6819df741470"
//        val numberOfAction = 250
//        getAlarmConditionByIdUseCase.invoke(
//            alarmConditionId = alarmConditionId,
//            numberOfActions = numberOfAction
//        )
//            .onEach { alarmConditionById ->
//                Log.d(TAG, "Alarm condition by Id response $alarmConditionById")
//            }
//            .launchIn(viewModelScope)
 }

}