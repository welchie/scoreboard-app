package uk.org.pentlandscouts.scoreboard_app.data

import kotlinx.serialization.Serializable

@Serializable
data class ActivityItem(
    val name: String,
    val description: String = "",
    val maxScore: Int,
    val zone: String,
    val id: Int
)
