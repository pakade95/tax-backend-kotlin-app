package pl.dtrzaskoma.taxbackendkotlinapp.app.services.counterState

import org.springframework.stereotype.Service
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.counter.Counter
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.counterState.CounterState
import pl.dtrzaskoma.taxbackendkotlinapp.app.repositories.counter.CounterRepository
import pl.dtrzaskoma.taxbackendkotlinapp.app.repositories.counterState.CounterStateRepository
import java.util.*

@Service
class CounterStateServiceImpl(
    private val counterStateRepository: CounterStateRepository,
    private val counterRepository: CounterRepository,
) {


}