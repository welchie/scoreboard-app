package uk.org.pentlandscouts.scoreboard_app.data

data class ActivityItem(
    val name: String,
    val description: String,
    val maxScore: Int,
    val zone: String,
    val ID: Int
)
