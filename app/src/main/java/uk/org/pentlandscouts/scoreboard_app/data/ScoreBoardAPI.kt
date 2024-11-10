package uk.org.pentlandscouts.scoreboard_app.data

import khttp.responses.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray


class ScoreBoardAPI {


    companion object {
        private val BASE_URL: String = "http://ec2-3-250-117-19.eu-west-1.compute.amazonaws.com:8080"
        private val SCOREBOARD_API_URL: String = "$BASE_URL/scoreboard/table"
        private val TEAMS_API_URL: String = "$BASE_URL/teams"
        private val ACTIVITIES_API_URL: String = "$BASE_URL/activities"

        suspend fun getScoreBoardData(): JSONArray {
            return withContext(Dispatchers.IO) { getScoreBoardDataList() }
        }

        suspend fun getScoreBoardDataList(): JSONArray {
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
            return getAPIData(ACTIVITIES_API_URL)
        }

        fun getAPIData(url: String): JSONArray {
            val response: Response =
                khttp.get(url)
            val statusCode = response.statusCode
            val data: JSONArray = response.jsonArray

            return data
        }
    }


}