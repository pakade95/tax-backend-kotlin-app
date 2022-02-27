package pl.dtrzaskoma.taxbackendkotlinapp.app.controllers.api.counterState

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.dtrzaskoma.taxbackendkotlinapp.app.controllers.api.AbstractExportController
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.counterState.CounterState
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.dtos.counterState.CounterStateReadModel
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.dtos.counterState.CounterStateWriteModel
import pl.dtrzaskoma.taxbackendkotlinapp.app.repositories.counterState.CounterStateRepository
import pl.dtrzaskoma.taxbackendkotlinapp.app.services.counterState.CounterStateService
import java.net.URI

@RestController
@RequestMapping(
    path = ["/api/state"],
    produces = [MediaType.APPLICATION_JSON_VALUE],
    consumes = [MediaType.APPLICATION_JSON_VALUE]
)
class ExportCounterStateController(
    private val counterStateService: CounterStateService,
    private val counterStateRepository: CounterStateRepository
) : AbstractExportController<CounterState>() {

    @GetMapping(path = ["/{counterId}"])
    fun getCounterStatesById(@PathVariable counterId: Long): ResponseEntity<List<CounterStateReadModel>> {
        val counterStates: List<CounterState> = counterStateRepository.findByCounterId(counterId)
        if (counterStates.isEmpty()) {
            return ResponseEntity.notFound().build()
        }
        val mappedCounterStates: List<CounterStateReadModel> =
            counterStates.map { counterState -> CounterStateReadModel.toCounterStateReadModel(counterState) }
        return ResponseEntity.ok(mappedCounterStates)
    }

    @PostMapping
    fun createCounterState(@RequestBody counterStateWriteModel: CounterStateWriteModel): ResponseEntity<*> {
        return counterStateService.saveNewCounterState(counterStateWriteModel)
            .map { counterState -> ResponseEntity.created(URI.create("/api/state" + counterState.id)).build<Any>() }
            .orElseGet { ResponseEntity.noContent().build<Any>() }
    }

    @DeleteMapping(path = ["/{id}"])
    fun deleteCounterStateById(@PathVariable id: Long): ResponseEntity<*> {
        if (counterStateRepository.existsById(id)) {
            try {
                counterStateRepository.deleteById(id)
            } finally {
                val properHttpStatus: HttpStatus = setProperHttpStatus(counterStateRepository.findById(id))
                return ResponseEntity<Any>(properHttpStatus)
            }
        }
        return ResponseEntity.notFound().build<Any>()
    }
}