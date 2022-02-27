package pl.dtrzaskoma.taxbackendkotlinapp.app.services.mock.counter;

import pl.dtrzaskoma.taxbackendkotlinapp.app.models.counter.Counter
import pl.dtrzaskoma.taxbackendkotlinapp.app.repositories.counter.CounterRepository
import pl.dtrzaskoma.taxbackendkotlinapp.app.services.mock.helpers.MockRepository
import java.util.*

class MockCounterRepository(list: MutableList<Counter>) : MockRepository<Counter>(), CounterRepository {

    init {
        mockDatabase.addAll(list)
    }

    override fun findByCounterName(counterName: String): Optional<Counter> {
        return mockDatabase.stream().filter { counter ->
            counterName == counter.counterName
        }.findFirst()
    }

    override fun existsByCounterName(counterName: String): Boolean {
        return findByCounterName(counterName).isPresent
    }

    override fun existsByCounterNameAndLocationIdAndOwnerId(
        counterName: String,
        locationId: Long,
        ownerId: Long
    ): Boolean {
        return mockDatabase.stream().filter { counter ->
            counterName == counter.counterName &&
                    locationId == counter.location.id &&
                    ownerId == counter.owner.id
        }.findFirst().isPresent
    }

    override fun findById(id: Long): Optional<Counter> {
        return mockDatabase.stream().filter { counter -> id == counter.id }.findFirst()
    }

    override fun deleteById(id: Long) {
        findById(id).map { counter -> mockDatabase.remove(counter) }
    }

    override fun existsById(id: Long): Boolean {
        return findById(id).isPresent
    }

    override fun save(element: Counter): Counter {
        element.id = (mockDatabase.size + 1).toLong()
        return super.save(element)
    }
}
