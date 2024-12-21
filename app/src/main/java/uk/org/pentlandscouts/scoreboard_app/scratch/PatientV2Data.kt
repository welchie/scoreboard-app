package uk.org.pentlandscouts.scoreboard_app.scratch

data class PatientV2Data(
    val patientId: String,
    val firstName: String,
    val lastName: String,
    val deviceId: String,
    val batteryLevel: Int? = 0,
    val locationId: String?,
    val location: String? = null,
    val datePaired: String?,
    val watching: Boolean = false,
    val currentRespiratoryValue: String?,
    val currentRespiratoryTime: String?,
    val previousRespiratoryValue: String?,
    val previousRespiratoryTime: String?,
    val currentSpo2Value: String?,
    val currentSpo2Time: String?,
    val previousSpo2Value: String?,
    val previousSpo2Time: String?,
    val currentPulseValue: String?,
    val currentPulseTime: String?,
    val previousPulseValue: String?,
    val previousPulseTime: String?,
    val currentSkinTempValue: String?,
    val currentSkinTempTime: String?,
    val previousSkinTempValue: String?,
    val previousSkinTempTime: String?,
    val currentBpValue: String?,
    val currentBpTime: String?,
    val previousBpValue: String?,
    val previousBpTime: String?,
    val currentMovementValue: String?,
    val currentMovementTime: String?,
    val previousMovementValue: String?,
    val previousMovementTime: String?,
    val covid19Status: String?

) {

    fun matchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            "$firstName$lastName",
            "$firstName $lastName",
            "${firstName.first()} ${lastName.first()}",
            "$patientId"
        )

        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}
