package pl.dtrzaskoma.taxbackendkotlinapp.app.controllers.api

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import pl.dtrzaskoma.taxbackendkotlinapp.app.controllers.api.exception.ExportResourceException
import java.util.*

abstract class AbstractExportController<T> {

    protected fun setProperHttpStatus(foundElement: Optional<T>): HttpStatus {
        return if (foundElement.isEmpty) HttpStatus.OK else HttpStatus.BAD_REQUEST
    }

    @ExceptionHandler(ExportResourceException::class)
    protected fun createExportCounterException(counterException: ExportResourceException): ResponseEntity<*> {
        val properStatus: HttpStatus = if (counterException.id != null) HttpStatus.NOT_FOUND else HttpStatus.BAD_REQUEST
        if (HttpStatus.NOT_FOUND == properStatus) {
            return ResponseEntity.notFound().build<Any>()
        } else {
            return ResponseEntity.badRequest().build<Any>()
        }
    }

}