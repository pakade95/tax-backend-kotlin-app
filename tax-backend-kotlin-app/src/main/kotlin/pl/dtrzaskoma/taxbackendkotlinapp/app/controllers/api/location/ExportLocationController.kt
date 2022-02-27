package pl.dtrzaskoma.taxbackendkotlinapp.app.controllers.api.location

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
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.dtos.location.LocationReadModel
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.dtos.location.LocationWriteModel
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.location.Location
import pl.dtrzaskoma.taxbackendkotlinapp.app.repositories.location.LocationRepository
import pl.dtrzaskoma.taxbackendkotlinapp.app.services.location.LocationService
import java.net.URI

@RestController
@RequestMapping(
    path = ["api/location"],
    produces = [MediaType.APPLICATION_JSON_VALUE],
    consumes = [MediaType.APPLICATION_JSON_VALUE]
)
class ExportLocationController(
    private val locationService: LocationService,
    private val locationRepository: LocationRepository
) : AbstractExportController<Location>() {

    @GetMapping
    fun getAllLocations(): ResponseEntity<*> {
        val locations: List<Location> = locationRepository.findAll();
        if (locations.isEmpty()) {
            return ResponseEntity.notFound().build<Any>()
        }
        val mappedLocations = locations.map { location ->
            LocationReadModel.toLocationReadModel(location)
        }.toList()
        return ResponseEntity.ok(mappedLocations)
    }

    @GetMapping(path = ["/{id}"])
    fun getLocationById(@PathVariable id: Long): ResponseEntity<*> {
        val foundLocationById: LocationReadModel = locationRepository.findById(id)
            .map { location -> LocationReadModel.toLocationReadModel(location) }
            .orElseThrow { ExportResourceException(id) }
        return ResponseEntity.ok(foundLocationById);
    }

    @PostMapping
    fun createLocation(@RequestBody locationWriteModel: LocationWriteModel): ResponseEntity<*> {
        return locationService.saveNewLocation(locationWriteModel)
            .map { location -> ResponseEntity.created(URI.create("/api/location" + location.id)).build<Any>() }
            .orElse(ResponseEntity.noContent().build<Any>())
    }

    @DeleteMapping(path = ["/{id}"])
    fun deleteLocationById(@PathVariable id: Long): ResponseEntity<*> {
        if (locationRepository.existsById(id)) {
            try {
                locationRepository.deleteById(id)
            } finally {
                val properStatus: HttpStatus = setProperHttpStatus(locationRepository.findById(id))
                return ResponseEntity<Any>(properStatus)
            }
        }
        return ResponseEntity.notFound().build<Any>()
    }

}