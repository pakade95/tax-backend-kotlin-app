package pl.dtrzaskoma.taxbackendkotlinapp.app.services.mock.counterStates

import pl.dtrzaskoma.taxbackendkotlinapp.app.models.counterState.CounterState
import pl.dtrzaskoma.taxbackendkotlinapp.app.repositories.counterState.CounterStateRepository
import pl.dtrzaskoma.taxbackendkotlinapp.app.services.mock.helpers.MockRepository
import java.util.*

class MockCounterStateRepository(list: MutableList<CounterState>) : CounterStateRepository,
    MockRepository<CounterState>() {

    init {
        mockDatabase.addAll(list)
    }

    override fun findByCounterId(counterId: Long): List<CounterState> {
        return mockDatabase.stream().filter { counterState ->
            counterId == counterState.counter?.id
        }.toList()
    }

    override fun deleteById(id: Long) {
        findById(id).map { counterState -> mockDatabase.remove(counterState) }
    }

    override fun findById(id: Long): Optional<CounterState> {
        return mockDatabase.stream()
            .filter { counterState -> id == counterState.id }
            .findFirst()
    }

    override fun existsById(id: Long): Boolean {
        return findById(id).isPresent
    }

    override fun save(element: CounterState): CounterState {
        element.id = (mockDatabase.size + 1).toLong()
        return super.save(element)
    }
}