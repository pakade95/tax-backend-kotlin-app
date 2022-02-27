package pl.dtrzaskoma.taxbackendkotlinapp.app.controllers.api.counter

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.dtrzaskoma.taxbackendkotlinapp.app.controllers.api.AbstractExportController
import pl.dtrzaskoma.taxbackendkotlinapp.app.controllers.api.exception.ExportResourceException
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.counter.Counter
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.dtos.counter.CounterReadModel
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.dtos.counter.CounterWriteModel
import pl.dtrzaskoma.taxbackendkotlinapp.app.repositories.counter.CounterRepository
import pl.dtrzaskoma.taxbackendkotlinapp.app.services.counter.CounterService
import java.net.URI

@RestController
@RequestMapping(
    path = ["/api/counter"],
    consumes = [MediaType.APPLICATION_JSON_VALUE],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class ExportCounterController(
    private val counterService: CounterService,
    private val counterRepository: CounterRepository
) : AbstractExportController<Counter>() {

    @GetMapping
    fun getAllCounters(): ResponseEntity<*> {
        val counters: List<Counter> = counterRepository.findAll()
        if (counters.isEmpty()) {
            return ResponseEntity.notFound().build<Any>()
        }
        val mappedCounters: List<CounterReadModel> =
            counters.map { counter -> CounterReadModel.toCounterReadModel(counter) }
        return ResponseEntity.ok(mappedCounters)
    }

    @GetMapping(path = ["/{id}"])
    fun getCounterById(@PathVariable id: Long): ResponseEntity<CounterReadModel> {
        val foundCounterById = counterRepository.findById(id)
            .map { counter -> CounterReadModel.toCounterReadModel(counter) }
            .orElseThrow { ExportResourceException(id) }
        return ResponseEntity.ok(foundCounterById)
    }

    @PostMapping
    fun createCounter(@RequestBody counterWriteModel: CounterWriteModel): ResponseEntity<*> {
        return counterService.saveNewCounter(counterWriteModel)
            .map { counter -> ResponseEntity.created(URI.create("/api/counter" + counter.id)).build<Any>() }
            .orElseGet { ResponseEntity.noContent().build<Any>() }
    }

    @DeleteMapping(path = ["/{id}"])
    fun deleteCounterById(@PathVariable id: Long): ResponseEntity<*> {
        if (counterRepository.existsById(id)) {
            try {
                counterRepository.deleteById(id)
            } finally {
                val properHttpStatus: HttpStatus = setProperHttpStatus(counterRepository.findById(id))
                return ResponseEntity<Any>(properHttpStatus)
            }
        }
        return ResponseEntity.notFound().build<Any>()
    }

}