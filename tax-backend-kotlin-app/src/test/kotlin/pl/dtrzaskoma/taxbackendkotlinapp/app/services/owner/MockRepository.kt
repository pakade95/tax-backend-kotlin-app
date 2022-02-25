package pl.dtrzaskoma.taxbackendkotlinapp.app.services.owner

abstract class MockRepository<T>() {

    var mockDatabase: MutableList<T> = mutableListOf()

    open fun save(element: T): T {
        mockDatabase.add(element)
        return element
    }

    fun findAll(): List<T> {
        return mockDatabase
    }
}