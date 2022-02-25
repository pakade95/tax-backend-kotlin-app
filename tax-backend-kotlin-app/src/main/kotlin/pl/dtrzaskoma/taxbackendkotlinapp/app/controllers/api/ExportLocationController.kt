package pl.dtrzaskoma.taxbackendkotlinapp.app.controllers.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.location.Location
import pl.dtrzaskoma.taxbackendkotlinapp.app.services.location.LocationService
import java.util.*

@RestController
@RequestMapping(path = ["/location"])
class ExportLocationController(private val locationService: LocationService) {

//    @GetMapping
//    fun showLocationList(): List<String> {
//        return locationService.getLocationNames();
//    }
//
//    @GetMapping("/add")
//    fun addLocation(): Optional<Location> {
//        val locationToSave =
//            Location
//                .Builder()
//                .postalCode("05-870")
//                .town("Blonie")
//                .street("Bieniewicka 21")
//                .build()
//
//        return locationService.saveNewLocation(locationToSave);
//    }
}