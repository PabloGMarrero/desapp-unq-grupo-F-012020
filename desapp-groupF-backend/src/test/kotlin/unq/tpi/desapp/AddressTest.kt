package unq.tpi.desapp

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

}