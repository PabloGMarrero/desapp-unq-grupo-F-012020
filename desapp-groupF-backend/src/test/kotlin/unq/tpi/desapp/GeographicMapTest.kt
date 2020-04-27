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
        assertEquals(aGeographicMap.latitude, 0.00)
        assertEquals(aGeographicMap.longitude, 0.00)
    }


    @Test
    fun testGeographicMapWitLatitude37dot187(){
        var aGeographicMap = GeographicMapBuilder.aGeographicMap().withLatitude(37.187).build()
        assertEquals(aGeographicMap.latitude, 37.187)
    }

    @Test
    fun testGeographicMapWitLongitude12dot33(){
        var aGeographicMap = GeographicMapBuilder.aGeographicMap().withLongitude(12.33).build()
        assertEquals(aGeographicMap.longitude, 12.33)
    }

    @Test
    fun testGeographicMapEquals(){
        var zoneA = GeographicMapBuilder.aGeographicMap().withLatitude(100.00).withLongitude(50.0).build()
        var zoneB = GeographicMapBuilder.aGeographicMap().withLatitude(100.00).withLongitude(50.0).build()
        var zoneC = GeographicMapBuilder.aGeographicMap().withLatitude(50.00).withLongitude(50.0).build()
        assertEquals(zoneA, zoneB)
        assertNotEquals(zoneA, zoneC)
        assertNotEquals(zoneB, zoneC)

    }
}