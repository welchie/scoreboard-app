package uk.org.pentlandscouts.scoreboard_app.data

import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import khttp.responses.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray


class ScoreBoardAPI {
    companion object {
        private const val BASE_URL: String = "http://ec2-3-250-117-19.eu-west-1.compute.amazonaws.com:8080"
        private const val SCOREBOARD_API_URL: String = "$BASE_URL/scoreboard/table"
        private const val TEAMS_API_URL: String = "$BASE_URL/teams"
        private const val ACTIVITIES_API_URL: String = "$BASE_URL/activities"
        private const val ADD_SCORE:String = "$BASE_URL/scoreboard/add"

        suspend fun getScoreBoardData(): JSONArray {
            return withContext(Dispatchers.IO) { getScoreBoardDataList() }
        }

        private fun getScoreBoardDataList(): JSONArray {
            return getScoreBoard()
        }

        fun getScoreBoard() : JSONArray
        {
            return getAPIData(SCOREBOARD_API_URL)
        }

        suspend fun getTeamsData(): JSONArray {
            return withContext(Dispatchers.IO) { getTeams() }
        }

        fun getTeams() : JSONArray
        {
            return getAPIData(TEAMS_API_URL)
        }

        suspend fun getActivitiesData(): JSONArray {
            return withContext(Dispatchers.IO) { getActivities() }
        }

        fun getActivities() : JSONArray
        {
            val activities:JSONArray = getAPIData(ACTIVITIES_API_URL)
            return activities
        }

        private fun getAPIData(url: String): JSONArray {
            val response: Response =
                khttp.get(url)
            val statusCode = response.statusCode
            val data: JSONArray = response.jsonArray

            return data
        }

        private fun update(url: String): Int {

            val policy = ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            val response: Response =
                khttp.get(url)
            val statusCode = response.statusCode

            return statusCode
        }

        fun addScore(teamID:Int, activityID:Int, score:Int): Int
        {
            val url = "$ADD_SCORE/$teamID/$activityID/$score"
            println("Add Score URL: $url")
            val statusCode = update(url)
            println("Status Code: $statusCode")
            return statusCode
        }
    }
}