package uk.org.pentlandscouts.scoreboard_app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import uk.org.pentlandscouts.scoreboard_app.data.ActivityItem
import uk.org.pentlandscouts.scoreboard_app.data.ScoreBoardAPI
import uk.org.pentlandscouts.scoreboard_app.data.ScoreBoardDataSource
import uk.org.pentlandscouts.scoreboard_app.data.ScoreItem
import uk.org.pentlandscouts.scoreboard_app.data.TeamItem

class ScoreboardViewModel(
    private val dataSource: ScoreBoardDataSource = ScoreBoardAPI,
    private val pollIntervalMs: Long = 60_000L,
    startPolling: Boolean = true
) : ViewModel() {

    sealed interface UiState {
        data object Loading : UiState
        data class Success(
            val scoreboard: List<ScoreItem>,
            val teams: List<TeamItem>,
            val activities: List<ActivityItem>
        ) : UiState
        data class Error(val message: String) : UiState
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        if (startPolling) {
            viewModelScope.launch {
                while (isActive) {
                    refreshData()
                    delay(pollIntervalMs)
                }
            }
        }
    }

    suspend fun refreshData() {
        runCatching {
            coroutineScope {
                val scoreboardDeferred = async { dataSource.getScoreBoardData() }
                val teamsDeferred = async { dataSource.getTeamsData() }
                val activitiesDeferred = async { dataSource.getActivitiesData() }

                UiState.Success(
                    scoreboard = scoreboardDeferred.await(),
                    teams = teamsDeferred.await(),
                    activities = activitiesDeferred.await()
                )
            }
        }.onSuccess { state ->
            _uiState.value = state
        }.onFailure { error ->
            if (_uiState.value !is UiState.Success) {
                _uiState.value = UiState.Error(error.message ?: "Failed to load scoreboard data")
            }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            refreshData()
        }
    }

    fun addScore(
        teamID: Int,
        actId: Int,
        score: Int,
        onResult: (Boolean) -> Unit = {}
    ) {
        viewModelScope.launch {
            val succeeded = runCatching {
                val status = dataSource.addScore(teamID, actId, score)
                status in 200..299
            }.getOrDefault(false)

            if (succeeded) {
                refreshData()
            }
            onResult(succeeded)
        }
    }
}
