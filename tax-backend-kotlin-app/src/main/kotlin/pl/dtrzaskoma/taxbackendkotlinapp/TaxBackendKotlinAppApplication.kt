package pl.dtrzaskoma.taxbackendkotlinapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TaxBackendKotlinAppApplication

fun main(args: Array<String>) {
	runApplication<TaxBackendKotlinAppApplication>(*args)
}
