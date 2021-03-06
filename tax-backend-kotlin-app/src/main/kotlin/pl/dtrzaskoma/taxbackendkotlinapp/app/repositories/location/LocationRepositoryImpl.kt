package pl.dtrzaskoma.taxbackendkotlinapp.app.repositories.location

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.location.Location

@Repository
interface LocationRepositoryImpl : LocationRepository, JpaRepository<Location, Long> {


}