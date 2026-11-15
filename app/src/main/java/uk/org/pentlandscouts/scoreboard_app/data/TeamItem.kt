package uk.org.pentlandscouts.scoreboard_app.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeamItem(
    @SerialName("name")
    val teamName: String,
    val id: Int,
    val logo: String
)
