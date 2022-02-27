package pl.dtrzaskoma.taxbackendkotlinapp.app.controllers.api.exception

import java.util.Objects
import java.util.function.Supplier

class ExportResourceException(val id: Long? = null) : RuntimeException(){

    object ExportExceptionDescription {
        const val NOT_FOUND_MESSAGE: String = "Nie udało sie zlokalizować zasobu o identyfikatorze: "
        const val REQUEST_ERROR_MESSAGE: String = "Błąd ogólny!";
    }

    init {
        var message: String = prepareMessage()
    }

    private fun prepareMessage(): String {
        val messageBuilder: StringBuilder = StringBuilder()
        if (this.id != null) {
            messageBuilder.append(ExportExceptionDescription.NOT_FOUND_MESSAGE).append(id)
        } else {
            messageBuilder.append(ExportExceptionDescription.REQUEST_ERROR_MESSAGE)
        }
        return messageBuilder.toString()
    }

}