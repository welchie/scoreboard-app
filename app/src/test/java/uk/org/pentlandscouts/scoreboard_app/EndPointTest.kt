package uk.org.pentlandscouts.scoreboard_app

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import uk.org.pentlandscouts.scoreboard_app.data.ActivityItem
import uk.org.pentlandscouts.scoreboard_app.data.ScoreBoardAPI
import uk.org.pentlandscouts.scoreboard_app.data.ScoreBoardDataSource
import uk.org.pentlandscouts.scoreboard_app.data.ScoreItem
import uk.org.pentlandscouts.scoreboard_app.data.TeamItem
import uk.org.pentlandscouts.scoreboard_app.ui.ScoreboardViewModel

@OptIn(ExperimentalCoroutinesApi::class)
class EndPointTest {

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun parseScoreboardJsonTest() {
        val json = """
            [
                {"teamName": "Lions", "numActivities": 3, "score": 75, "teamLogo": "lion_foreground"},
                {"teamName": "Owls", "numActivities": 2, "score": 50, "teamLogo": "owl_foreground"}
            ]
        """.trimIndent()

        val result = ScoreBoardAPI.parseScoreBoardJson(json)
        assertEquals(2, result.size)
        assertEquals(ScoreItem("Lions", 3, 75, "lion_foreground"), result[0])
        assertEquals(ScoreItem("Owls", 2, 50, "owl_foreground"), result[1])
    }

    @Test
    fun parseTeamsJsonTest() {
        val json = """
            [
                {"name": "Lions", "id": 1, "logo": "lion_foreground"},
                {"name": "Sharks", "id": 2, "logo": "shark_foreground"}
            ]
        """.trimIndent()

        val result = ScoreBoardAPI.parseTeamsJson(json)
        assertEquals(2, result.size)
        assertEquals(TeamItem("Lions", 1, "lion_foreground"), result[0])
        assertEquals(TeamItem("Sharks", 2, "shark_foreground"), result[1])
    }

    @Test
    fun parseActivitiesJsonTest() {
        val json = """
            [
                {"name": "Archery", "description": "Target archery", "maxScore": 50, "zone": "North", "id": 10}
            ]
        """.trimIndent()

        val result = ScoreBoardAPI.parseActivitiesJson(json)
        assertEquals(1, result.size)
        assertEquals(ActivityItem("Archery", "Target archery", 50, "North", 10), result[0])
    }

    @Test
    fun viewModelRefreshesAfterAddScoreTest() = runTest(testDispatcher) {
        var currentScore = 50
        val fakeDataSource = object : ScoreBoardDataSource {
            override suspend fun getScoreBoardData(): List<ScoreItem> =
                listOf(ScoreItem("Lions", 1, currentScore, "lion_foreground"))

            override suspend fun getTeamsData(): List<TeamItem> =
                listOf(TeamItem("Lions", 1, "lion_foreground"))

            override suspend fun getActivitiesData(): List<ActivityItem> =
                listOf(ActivityItem("Archery", "Target archery", 50, "North", 10))

            override suspend fun addScore(teamID: Int, activityID: Int, score: Int): Int {
                currentScore += score
                return 200
            }
        }

        val viewModel = ScoreboardViewModel(
            dataSource = fakeDataSource,
            startPolling = false
        )

        viewModel.refreshData()
        val initialState = viewModel.uiState.value
        assertTrue(initialState is ScoreboardViewModel.UiState.Success)
        assertEquals(50, (initialState as ScoreboardViewModel.UiState.Success).scoreboard.first().score)

        var callbackResult = false
        viewModel.addScore(teamID = 1, actId = 10, score = 25) { succeeded ->
            callbackResult = succeeded
        }
        testDispatcher.scheduler.advanceUntilIdle()

        assertTrue(callbackResult)
        val updatedState = viewModel.uiState.value as ScoreboardViewModel.UiState.Success
        assertEquals(75, updatedState.scoreboard.first().score)
    }
}
