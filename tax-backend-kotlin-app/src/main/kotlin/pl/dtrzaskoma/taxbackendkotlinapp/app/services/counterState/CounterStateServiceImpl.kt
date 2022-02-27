package pl.dtrzaskoma.taxbackendkotlinapp.app.services.counterState

import org.springframework.stereotype.Service
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.counter.Counter
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.counterState.CounterState
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.dtos.counterState.CounterStateWriteModel
import pl.dtrzaskoma.taxbackendkotlinapp.app.repositories.counter.CounterRepository
import pl.dtrzaskoma.taxbackendkotlinapp.app.repositories.counterState.CounterStateRepository
import java.util.*

@Service
class CounterStateServiceImpl(
    private val counterStateRepository: CounterStateRepository,
    private val counterRepository: CounterRepository,
) : CounterStateService {

    override fun saveNewCounterState(counterToWrite: CounterStateWriteModel): Optional<CounterState> {
        val counter: Optional<Counter> = counterRepository.findByCounterName(counterToWrite.counter)
        var entity: CounterState? = null
        var counterToSave: CounterState = CounterState()
        if (counter.isPresent) {
            counterToSave.counter = counter.get()
            setValuestoCounterState(counterToWrite, counterToSave)
            entity = counterStateRepository.save(counterToSave)
        }
        return Optional.ofNullable(entity)
    }

    private fun setValuestoCounterState(source: CounterStateWriteModel, target: CounterState) {
        target.state = source.state
        target.date = source.date
    }

}