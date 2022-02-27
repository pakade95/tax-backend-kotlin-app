package pl.dtrzaskoma.taxbackendkotlinapp.app.services.counter

import pl.dtrzaskoma.taxbackendkotlinapp.app.models.counter.Counter
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.counterState.CounterState
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.dtos.counter.CounterWriteModel
import java.util.*

interface CounterService {

    fun getCounterNames(): List<String>
    fun saveNewCounter(counterToWrite: CounterWriteModel): Optional<Counter>
    fun findCounter(counterName: String): Optional<Counter>
    fun findAllCounterStatesByCounterName(counterName: String): Optional<List<CounterState>>

}