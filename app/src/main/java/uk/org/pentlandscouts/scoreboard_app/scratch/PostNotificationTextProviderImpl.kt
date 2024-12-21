package uk.org.pentlandscouts.scoreboard_app.scratch

import android.content.Context
import com.example.scoreboard_app.R


class PostNotificationTextProviderImpl(private val context: Context) : PostNotificationTextProvider {

    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if(isPermanentlyDeclined) {
            context.getString(R.string.post_notification_permanently_declined)
        } else {
            context.getString(R.string.post_notification_request)
        }
    }
}