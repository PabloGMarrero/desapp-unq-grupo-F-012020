package unq.tpi.desapp

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import unq.tpi.desapp.builders.GeographicMapBuilder

@SpringBootTest
class GeographicMapTest {

    @Test
    fun testGeographicMap(){
        var aGeographicMap = GeographicMapBuilder.aGeographicMap().build()
        assertEquals(aGeographicMap.address, "")
        assertEquals(aGeographicMap.latitude, 0.00)
        assertEquals(aGeographicMap.longitude, 0.00)
    }

    @Test
    fun testGeographicMapWithAddressSanMartin(){
        var aGeographicMap = GeographicMapBuilder.aGeographicMap().withAddress("San Martin").build()
        assertEquals(aGeographicMap.address, "San Martin")
    }

    @Test
    fun testGeographicMapWitLatitude37dot187(){
        var aGeographicMap = GeographicMapBuilder.aGeographicMap().withLatitude(37.187).build()
        assertEquals(aGeographicMap.latitude, 37.187)
    }

    @Test
    fun testGeographicMapWitLongitude12dot33(){
        var aGeographicMap = GeographicMapBuilder.aGeographicMap().withLatitude(12.33).build()
        assertEquals(aGeographicMap.latitude, 12.33)
    }
}