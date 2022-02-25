package pl.dtrzaskoma.taxbackendkotlinapp.app.repositories.counter

import pl.dtrzaskoma.taxbackendkotlinapp.app.models.counter.Counter
import java.util.*

interface CounterRepository {

    fun findAll(): List<Counter>
    fun findByCounterName(counterName: String): Optional<Counter>
    fun save(counter: Counter): Counter
    fun existsByCounterName(counterName: String): Boolean
    fun existsByCounterNameAndLocationIdAndOwnerId(counterName: String, locationId: Long, ownerId: Long): Boolean
    fun findById(id: Long): Optional<Counter>
    fun deleteById(id: Long): Unit
    fun existsById(id: Long): Boolean
}