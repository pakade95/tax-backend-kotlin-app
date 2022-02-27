package pl.dtrzaskoma.taxbackendkotlinapp.app.services.unitTest;

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import pl.dtrzaskoma.taxbackendkotlinapp.app.services.location.LocationService
import pl.dtrzaskoma.taxbackendkotlinapp.app.services.location.LocationServiceImpl
import pl.dtrzaskoma.taxbackendkotlinapp.app.services.mock.helpers.AbstractServiceTest
import pl.dtrzaskoma.taxbackendkotlinapp.app.services.mock.location.MockLocationRepository

class LocationServiceImplTest : AbstractServiceTest() {

    @Test
    @DisplayName("should show all locations")
    fun getLocationNames_locationsFound() {
        //given
        val mockLocationRepository = MockLocationRepository(locationsList())
        val locationService: LocationService = LocationServiceImpl(mockLocationRepository)
        //when
        val result: List<String> = locationService.getLocationNames()
        //then
        assertFalse(result.isEmpty());
        assertEquals(3, result.size)
    }

    @Test
    @DisplayName("should not show nothing")
    fun getLocationNames_locationsNotFound() {
        //given
        val mockLocationRepository = MockLocationRepository(mutableListOf())
        val locationService: LocationService = LocationServiceImpl(mockLocationRepository)
        //when
        val result: List<String> = locationService.getLocationNames()
        //then
        assertTrue(result.isEmpty())
    }

}
