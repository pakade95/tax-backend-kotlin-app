package pl.dtrzaskoma.taxbackendkotlinapp.app.repositories.Location

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.Location.Location

@Repository
interface LocationRepositoryImpl : LocationRepository, JpaRepository<Location, Long> {


}