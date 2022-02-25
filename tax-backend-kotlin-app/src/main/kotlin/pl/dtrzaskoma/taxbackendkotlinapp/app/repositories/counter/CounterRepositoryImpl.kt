package pl.dtrzaskoma.taxbackendkotlinapp.app.repositories.counter

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.counter.Counter

@Repository
interface CounterRepositoryImpl : CounterRepository, JpaRepository<Counter, Long> {
}