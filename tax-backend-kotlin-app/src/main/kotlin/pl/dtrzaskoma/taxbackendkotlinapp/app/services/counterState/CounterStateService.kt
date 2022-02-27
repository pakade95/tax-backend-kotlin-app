package pl.dtrzaskoma.taxbackendkotlinapp.app.services.counterState

import pl.dtrzaskoma.taxbackendkotlinapp.app.models.counterState.CounterState
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.dtos.counterState.CounterStateWriteModel
import java.util.*

interface CounterStateService {

    fun saveNewCounterState(counterToWrite:CounterStateWriteModel): Optional<CounterState>
}