package unq.tpi.desapp.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.springframework.boot.test.context.SpringBootTest
import unq.tpi.desapp.builders.AddressBuilder
import unq.tpi.desapp.builders.GeographicMapBuilder

@SpringBootTest
class AddressTest {

    @Test
    fun testBasicAddress(){
        var address = AddressBuilder.anAddress().build()
        var zone = GeographicMapBuilder.aGeographicMap().build()
        assertEquals(address.locality, "")
        assertEquals(address.street, "")
        assertEquals(address.number, 0)
        assertEquals(address.geographicZone, zone)
    }

    @Test
    fun testAddresWithLocalityQuilmes(){
        var address = AddressBuilder.anAddress().withLocality("Quilmes").build()
        assertEquals(address.locality, "Quilmes")
    }

    @Test
    fun testAddresWithStreetRivadavia(){
        var address = AddressBuilder.anAddress().withStreet("Rivadavia").build()
        assertEquals(address.street, "Rivadavia")
    }

    @Test
    fun testAddresWithNumber150(){
        var address = AddressBuilder.anAddress().withNumber(150).build()
        assertEquals(address.number, 150)
    }

    @Test
    fun testAddresWithZone(){
        var zone = GeographicMapBuilder.aGeographicMap().withLatitude(41.0).withLongitude(35.0).build()
        var address = AddressBuilder.anAddress().withZone(zone).build()

        assertEquals(address.geographicZone, zone)
    }

}