package pl.dtrzaskoma.taxbackendkotlinapp.app.repositories.counterState

import pl.dtrzaskoma.taxbackendkotlinapp.app.models.counterState.CounterState

interface CounterStateRepository {

    fun findAll(): List<CounterState>
    fun save(counterState: CounterState): CounterState
    fun findByCounterId(counterId: Long): List<CounterState>
    fun deleteById(id: Long): Unit
    fun findById(id: Long): Unit
    fun existsById(id: Long): Boolean

}