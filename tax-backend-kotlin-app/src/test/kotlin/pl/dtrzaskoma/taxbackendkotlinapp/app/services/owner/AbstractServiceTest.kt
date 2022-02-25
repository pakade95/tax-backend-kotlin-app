package pl.dtrzaskoma.taxbackendkotlinapp.app.services.owner

import pl.dtrzaskoma.taxbackendkotlinapp.app.models.owner.Owner

abstract class AbstractServiceTest {

    protected fun ownersList(): MutableList<Owner> {
        val firstExample: Owner = prepareOwner(1L, "John", "Foo", "jFoo@mail.com")
        val secondExample: Owner = prepareOwner(2L, "William", "Bar", "wBar@mail.com")
        val thirdExample: Owner = prepareOwner(3L, "John", "Wick", "jWick@mail.com")
        return mutableListOf(firstExample, secondExample, thirdExample);
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