package uk.org.pentlandscouts.scoreboard_app.ui.navapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.withContext
import org.json.JSONArray
import uk.org.pentlandscouts.scoreboard_app.data.ScoreBoardAPI
import uk.org.pentlandscouts.scoreboard_app.data.ScoreBoardAPI.Companion

class ScoreboardViewModel : ViewModel()
{

    //var data:JSONArray = JSONArray()
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading
        .onStart {
            getScoreBoardData()
            println("Loading scoreboard data...")
            //ScoreBoardAPI.getTeams()
            //println(data.toString())
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(10000L),
            false
        )


    suspend fun getScoreBoardData() :JSONArray{
        delay(1000) // simulate a long-running operation
        //Build Score Data here
        return withContext(Dispatchers.IO) {
            ScoreBoardAPI.getScoreBoardData()
        }
    }

}