package pl.dtrzaskoma.taxbackendkotlinapp.app.controllers.api.owner

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
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.dtos.owner.OwnerReadModel
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.dtos.owner.OwnerWriteModel
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.owner.Owner
import pl.dtrzaskoma.taxbackendkotlinapp.app.repositories.owner.OwnerRepository
import pl.dtrzaskoma.taxbackendkotlinapp.app.services.owner.OwnerService
import java.net.URI

@RestController
@RequestMapping(
    path = ["/api/owner"],
    consumes = [MediaType.APPLICATION_JSON_VALUE],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class ExportOwnerController(private val ownerRepository: OwnerRepository, private val ownerService: OwnerService) :
    AbstractExportController<Owner>() {

    @GetMapping
    fun getAllOwners(): ResponseEntity<List<OwnerReadModel>> {
        val owners = ownerRepository.findAll()
        if (owners.isEmpty()) {
            return ResponseEntity.notFound().build()
        }
        val mappedOwners: List<OwnerReadModel> = owners.stream()
            .map { owner -> OwnerReadModel.toOwnerReadModel(owner) }
            .toList()
        return ResponseEntity.ok(mappedOwners)
    }

    @GetMapping(path = ["/{id}"])
    fun getOwnerById(@PathVariable id: Long): ResponseEntity<OwnerReadModel> {
        val foundOwnerById: OwnerReadModel = ownerRepository.findById(id)
            .map { owner -> OwnerReadModel.toOwnerReadModel(owner) }
            .orElseThrow { ExportResourceException(id) }
        return ResponseEntity.ok(foundOwnerById);
    }

    @PostMapping
    fun createOwner(@RequestBody ownerWriteModel: OwnerWriteModel): ResponseEntity<*> {
        return ownerService.saveNewOwner(ownerWriteModel)
            .map { owner -> ResponseEntity.created(URI.create("api/owner/" + owner.id)).build<Any>() }
            .orElse(ResponseEntity.noContent().build())
    }

    @DeleteMapping
    fun deleteOwnerById(@PathVariable id: Long): ResponseEntity<*> {
        if (ownerRepository.existsById(id)) {
            try {
                ownerRepository.deleteById(id)
            } finally {
                val properHttpStatus: HttpStatus = setProperHttpStatus(ownerRepository.findById(id))
                return ResponseEntity<Any>(properHttpStatus)
            }
        }
        return ResponseEntity.notFound().build<Any>()
    }


}