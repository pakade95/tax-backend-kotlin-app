package pl.dtrzaskoma.taxbackendkotlinapp.app.services.unitTest

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import pl.dtrzaskoma.taxbackendkotlinapp.app.repositories.owner.OwnerRepository
import pl.dtrzaskoma.taxbackendkotlinapp.app.services.mock.helpers.AbstractServiceTest
import pl.dtrzaskoma.taxbackendkotlinapp.app.services.mock.owner.MockOwnerRepository
import pl.dtrzaskoma.taxbackendkotlinapp.app.services.owner.OwnerServiceImpl

internal class OwnerServiceImplTest : AbstractServiceTest() {

    @Test
    @DisplayName("should show all owners")
    fun getOwnerCredentials_ownersFound() {
        //given
        val mockOwnerRepository: OwnerRepository = MockOwnerRepository(ownersList())
        val ownerService: OwnerServiceImpl = OwnerServiceImpl(mockOwnerRepository)
        //when
        val result: List<String> = ownerService.getOwnerCredentials()
        //then
        assertFalse(result.isEmpty())
        assertEquals(3, result.size)
    }

    @Test
    @DisplayName("should not show nothing when owners not found")
    fun getOwnerCredentials_ownersNotFound() {
        //given
        val mockOwnerRepository: OwnerRepository = MockOwnerRepository(mutableListOf())
        val ownerService: OwnerServiceImpl = OwnerServiceImpl(mockOwnerRepository)
        //when
        val result: List<String> = ownerService.getOwnerCredentials();
        //then
        assertTrue(result.isEmpty())
    }
}