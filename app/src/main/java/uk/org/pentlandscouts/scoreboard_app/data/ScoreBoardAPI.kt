package uk.org.pentlandscouts.scoreboard_app.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import uk.org.pentlandscouts.scoreboard_app.BuildConfig
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

interface ScoreBoardDataSource {
    suspend fun getScoreBoardData(): List<ScoreItem>
    suspend fun getTeamsData(): List<TeamItem>
    suspend fun getActivitiesData(): List<ActivityItem>
    suspend fun addScore(teamID: Int, activityID: Int, score: Int): Int
}

object ScoreBoardAPI : ScoreBoardDataSource {
    private const val TIMEOUT_MS = 10_000
    private val BASE_URL: String = BuildConfig.BASE_URL
    private val SCOREBOARD_API_URL: String = "$BASE_URL/scoreboard/table"
    private val TEAMS_API_URL: String = "$BASE_URL/teams"
    private val ACTIVITIES_API_URL: String = "$BASE_URL/activities"
    private val ADD_SCORE_URL: String = "$BASE_URL/scoreboard/add"

    internal val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        coerceInputValues = true
    }

    override suspend fun getScoreBoardData(): List<ScoreItem> = withContext(Dispatchers.IO) {
        parseScoreBoardJson(fetchBody(SCOREBOARD_API_URL))
    }

    override suspend fun getTeamsData(): List<TeamItem> = withContext(Dispatchers.IO) {
        parseTeamsJson(fetchBody(TEAMS_API_URL))
    }

    override suspend fun getActivitiesData(): List<ActivityItem> = withContext(Dispatchers.IO) {
        parseActivitiesJson(fetchBody(ACTIVITIES_API_URL))
    }

    override suspend fun addScore(teamID: Int, activityID: Int, score: Int): Int =
        withContext(Dispatchers.IO) {
            val url = "$ADD_SCORE_URL/$teamID/$activityID/$score"
            executeRequestStatusCode(url)
        }

    internal fun parseScoreBoardJson(payload: String): List<ScoreItem> =
        json.decodeFromString(payload)

    internal fun parseTeamsJson(payload: String): List<TeamItem> =
        json.decodeFromString(payload)

    internal fun parseActivitiesJson(payload: String): List<ActivityItem> =
        json.decodeFromString(payload)

    private fun fetchBody(urlString: String): String {
        val connection = (URL(urlString).openConnection() as HttpURLConnection).apply {
            requestMethod = "GET"
            connectTimeout = TIMEOUT_MS
            readTimeout = TIMEOUT_MS
            setRequestProperty("Accept", "application/json")
        }
        try {
            val statusCode = connection.responseCode
            if (statusCode !in 200..299) {
                throw IOException("HTTP error $statusCode fetching $urlString")
            }
            return connection.inputStream.bufferedReader().use { it.readText() }
        } finally {
            connection.disconnect()
        }
    }

    private fun executeRequestStatusCode(urlString: String): Int {
        val connection = (URL(urlString).openConnection() as HttpURLConnection).apply {
            requestMethod = "GET"
            connectTimeout = TIMEOUT_MS
            readTimeout = TIMEOUT_MS
        }
        try {
            return connection.responseCode
        } finally {
            connection.disconnect()
        }
    }
}
