package uk.org.pentlandscouts.scoreboard_app.data

// on below line we are creating a
// modal class for our data class.
data class ScoreItem(

    val teamName: String,

    val numActivities: Int,

    val score: Int,

    val teamImg: String
)