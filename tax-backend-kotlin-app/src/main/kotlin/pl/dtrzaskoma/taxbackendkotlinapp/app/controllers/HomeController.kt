package pl.dtrzaskoma.taxbackendkotlinapp.app.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.owner.Owner
import pl.dtrzaskoma.taxbackendkotlinapp.app.repositories.Owner.OwnerRepository

@RestController
class HomeController(val ownerRepository: OwnerRepository) {

    @GetMapping("/home")
    fun sayHi(): Owner {
        val owner = Owner("Jacek", "Trzaskoma", "jTrzaskoma@gmail.com")
        return ownerRepository.save(owner)
    }
}