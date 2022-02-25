package pl.dtrzaskoma.taxbackendkotlinapp.app.repositories.counterState

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.counterState.CounterState

@Repository
interface CounterStateRepositoryImpl : CounterStateRepository, JpaRepository<CounterState, Long> {
}