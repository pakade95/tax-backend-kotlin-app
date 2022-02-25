package pl.dtrzaskoma.taxbackendkotlinapp.app.models.dtos.counter

import pl.dtrzaskoma.taxbackendkotlinapp.app.models.counter.Counter
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.counterState.CounterState
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.dtos.counterState.CounterStateReadModel
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.dtos.counterState.CounterStateReadModel.Companion.toCounterStateReadModel
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.dtos.location.LocationReadModel
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.dtos.location.LocationReadModel.Companion.toLocationReadModel
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.dtos.owner.OwnerReadModel
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.dtos.owner.OwnerReadModel.Companion.toOwnerReadModel
import java.util.Comparator

class CounterReadModel(
    var id: Long = 0,
    var name: String = "",
    var owner: OwnerReadModel = OwnerReadModel(),
    var location: LocationReadModel = LocationReadModel(),
    var lastState: CounterStateReadModel = CounterStateReadModel()
) {

    companion object {
        fun toCounterReadModel(source: Counter): CounterReadModel {
            var lastState: CounterState = source.counterState
                .stream()
                .max(Comparator.comparing(CounterState::date))
                .orElse(CounterState())

            return CounterReadModel(
                source.id,
                source.counterName,
                toOwnerReadModel(source.owner),
                toLocationReadModel(source.location),
                toCounterStateReadModel(lastState)
            )
        }
    }
}