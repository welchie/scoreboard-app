package uk.org.pentlandscouts.scoreboard_app.view

import androidx.lifecycle.Lifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch


// Class that centralizes when the content of the app needs to be refreshed
class DataHandler(
    private val externalScope: CoroutineScope,
    private val tickIntervalMs: Long = 5000
) {
    // Backing property to avoid flow emissions from other classes
    private val _dataFlow = MutableSharedFlow<Unit>(replay = 1)
    val dataFlow: MutableSharedFlow<Unit> = _dataFlow

    init {
        externalScope.launch {
            while(true) {
                _dataFlow.emit(Unit)
                delay(tickIntervalMs)
            }
        }
    }
}