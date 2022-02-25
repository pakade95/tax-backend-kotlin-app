package pl.dtrzaskoma.taxbackendkotlinapp.app.services.counter

import org.springframework.stereotype.Service
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.counter.Counter
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.counterState.CounterState
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

    override fun saveNewCounter(counterToWrite: Counter): Optional<Counter> {
        var savedEntity:Counter? = null
        if(!checkIfCounterExists(counterToWrite)){
            savedEntity = counterRepository.save(counterToWrite)
        }
        return Optional.ofNullable(counterToWrite)
    }

    override fun findCounter(counterName: String): Optional<Counter> {
        return counterRepository.findByCounterName(counterName)
    }

    override fun findAllCounterStatesByCounterName(counterName: String): Optional<List<CounterState>> {
        return counterRepository.findByCounterName(counterName).map(Counter::counterState)
    }

    private fun checkIfCounterExists(counter: Counter): Boolean {
        return counterRepository.existsByCounterNameAndLocationIdAndOwnerId(
            counter.counterName,
            counter.location.id,
            counter.owner.id
        )
    }


}