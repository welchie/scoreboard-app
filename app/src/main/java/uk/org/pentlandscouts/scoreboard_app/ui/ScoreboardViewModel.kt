package uk.org.pentlandscouts.scoreboard_app.ui

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

class ScoreboardViewModel : ViewModel(), LifecycleObserver
{

    sealed class State {
        data object Loading: State()
        data class Data(val data: String): State()
    }

    var scoreboardData:JSONArray = JSONArray()
    var teamsData:JSONArray = JSONArray()
    var activities:JSONArray = JSONArray()
    private var _isLoading = MutableStateFlow<State>(State.Loading)
    val isLoading = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            while (isActive) {
                scoreboardData = getScoreBoardData()
                teamsData = getTeamsData()
                activities = getActivitiesData()

                _isLoading.value = State.Data("True")
                // wait one minute and repeat your request
                delay(60 * 1000L)
            }
        }
    }

    fun addScore(teamID:Int, actId:Int, score:Int): Int
    {
        return ScoreBoardAPI.addScore(teamID,actId,score)
    }

}