package pl.dtrzaskoma.taxbackendkotlinapp.app.services.counter

import org.springframework.stereotype.Service
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.counter.Counter
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.counterState.CounterState
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.dtos.counter.CounterWriteModel
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.location.Location
import pl.dtrzaskoma.taxbackendkotlinapp.app.repositories.counter.CounterRepository
import pl.dtrzaskoma.taxbackendkotlinapp.app.repositories.location.LocationRepository
import pl.dtrzaskoma.taxbackendkotlinapp.app.repositories.owner.OwnerRepository
import java.util.*

@Service
class CounterServiceImpl(
    private val counterRepository: CounterRepository,
    private val locationRepository: LocationRepository,
    private val ownerRepository: OwnerRepository,
) : CounterService {

    override fun getCounterNames(): List<String> {
        return counterRepository.findAll().map(Counter::counterName)
    }

    //to trzeba poprawic
    override fun saveNewCounter(counterToWrite: CounterWriteModel): Optional<Counter> {
        var savedEntity: Counter? = null
        if (!checkIfCounterExists(counterToWrite)) {
            val counterToSave: Counter =
                Counter(counterToWrite.name, emptyList(), counterToWrite.location, counterToWrite.location)
            savedEntity = counterRepository.save(counterToSave)
        }
        return Optional.ofNullable(savedEntity)
    }

    override fun findCounter(counterName: String): Optional<Counter> {
        return counterRepository.findByCounterName(counterName)
    }

    override fun findAllCounterStatesByCounterName(counterName: String): Optional<List<CounterState>> {
        return counterRepository.findByCounterName(counterName).map(Counter::counterState)
    }

    private fun checkIfCounterExists(counter: CounterWriteModel): Boolean {
        return counterRepository.existsByCounterNameAndLocationIdAndOwnerId(
            counter.counterName,
            counter.location.id,
            counter.owner.id
        )
    }
}