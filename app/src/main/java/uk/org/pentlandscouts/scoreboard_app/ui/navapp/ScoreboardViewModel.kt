package uk.org.pentlandscouts.scoreboard_app.ui.navapp

import android.os.StrictMode
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
import uk.org.pentlandscouts.scoreboard_app.data.ScoreBoardAPI.Companion.getScoreBoardData

class ScoreboardViewModel : ViewModel()
{

    var data:JSONArray = JSONArray()
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading
        .onStart {
            data = getScoreBoardData()
            println("Loading scoreboard data...")
//            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
//            StrictMode.setThreadPolicy(policy)
//            ScoreBoardAPI.getTeams()
            println(data.toString())
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            false
        )

}