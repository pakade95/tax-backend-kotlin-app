package pl.dtrzaskoma.taxbackendkotlinapp.app.services.mock.helpers

import pl.dtrzaskoma.taxbackendkotlinapp.app.models.location.Location
import pl.dtrzaskoma.taxbackendkotlinapp.app.models.owner.Owner

abstract class AbstractServiceTest {

    protected fun ownersList(): MutableList<Owner> {
        val firstExample: Owner = prepareOwner(1L, "John", "Foo", "jFoo@mail.com")
        val secondExample: Owner = prepareOwner(2L, "William", "Bar", "wBar@mail.com")
        val thirdExample: Owner = prepareOwner(3L, "John", "Wick", "jWick@mail.com")
        return mutableListOf(firstExample, secondExample, thirdExample);
    }

    protected fun locationsList():MutableList<Location>{
        val firstExample = prepareLocation(1L, "01-100", "Street 1", "Town 1")
        val secondExample = prepareLocation(2L, "01-200", "Street 2", "Town 2")
        val thirdExample = prepareLocation(3L, "01-300", "Street 3", "Town 3")
        return mutableListOf(firstExample, secondExample, thirdExample);
    }

    private fun prepareLocation(locationId:Long, postalCode:String, street:String, town:String):Location{
        return Location.Builder()
            .id(locationId)
            .street(street)
            .postalCode(postalCode)
            .town(town)
            .counterList(listOf())
            .build()
    }

    private fun prepareOwner(ownerId: Long, firstName: String, lastName: String, emailAddress: String): Owner {
        return Owner.Builder()
            .id(ownerId)
            .firstName(firstName)
            .emailAddress(emailAddress)
            .lastName(lastName)
            .counterList(listOf())
            .build()
    }
}