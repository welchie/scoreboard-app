package uk.org.pentlandscouts.scoreboard_app.scratch

interface PostNotificationTextProvider {

    fun getDescription(isPermanentlyDeclined : Boolean): String
}