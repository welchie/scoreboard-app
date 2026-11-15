package uk.org.pentlandscouts.scoreboard_app.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScoreItem(
    val teamName: String,
    val numActivities: Int,
    val score: Int,
    @SerialName("teamLogo")
    val teamImg: String
)
