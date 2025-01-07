package uk.org.pentlandscouts.scoreboard_app.view

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.json.JSONArray
import uk.org.pentlandscouts.scoreboard_app.data.ScoreBoardAPI
import uk.org.pentlandscouts.scoreboard_app.data.ScoreBoardAPI.Companion.getActivitiesData
import uk.org.pentlandscouts.scoreboard_app.data.ScoreBoardAPI.Companion.getScoreBoardData
import uk.org.pentlandscouts.scoreboard_app.data.ScoreBoardAPI.Companion.getTeamsData
import uk.org.pentlandscouts.scoreboard_app.data.ScoreItem

class ScoreboardViewModel : ViewModel(), LifecycleObserver
{

    sealed class State {
        data object Loading: State()
        data class Data(val data: String): State()
    }

    var scoreboardData:JSONArray = JSONArray()

    var teamsData:JSONArray = JSONArray()
    var activities:JSONArray = JSONArray()
    var _isLoading = MutableStateFlow<State>(State.Loading)
    val isLoading = _isLoading.asStateFlow()

    var selectedTeamID: Int = 0
    var selectedTeamName: String = ""

    private val dataHandler: DataHandler = DataHandler(
        externalScope = viewModelScope,
        tickIntervalMs = 5000L
    )
    init {
        viewModelScope.launch {
            dataHandler.dataFlow.collect {
                //refreshLatestNews()
                println("Getting Scoreboard data")
                scoreboardData = getScoreBoardData()
                println("Data retrieved $scoreboardData")
                teamsData = getTeamsData()
                activities = getActivitiesData()

                _isLoading.value = State.Data("True")
                // wait one minute and repeat your request
                delay(10 * 1000L)

            }
        }
    }

    fun addScore(teamID:Int, actId:Int, score:Int): Int
    {
        println("Adding Score $teamID, $actId, $score")
        return ScoreBoardAPI.addScore(teamID,actId,score)
    }

}