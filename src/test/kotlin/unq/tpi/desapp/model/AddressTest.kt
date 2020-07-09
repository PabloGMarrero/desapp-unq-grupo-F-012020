package unq.tpi.desapp.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.springframework.boot.test.context.SpringBootTest
import unq.tpi.desapp.builders.AddressBuilder
import unq.tpi.desapp.builders.GeographicMapBuilder
import unq.tpi.desapp.exceptions.InvalidLocalityAddressException
import unq.tpi.desapp.exceptions.InvalidNumberAddressException
import unq.tpi.desapp.exceptions.InvalidStreetAddressException
import kotlin.test.assertFailsWith

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

    @Test
    fun testTwoBasicAddressWithSameStateAreEqualAddress(){
        var address = AddressBuilder.anAddress().build()
        var anotherAddress = AddressBuilder.anAddress().build()

        assertEquals(address, anotherAddress)
    }

    @Test
    fun testTwoBasicAddressWithDifferentAddressNumberAreNotEqualAddress(){
        var address = AddressBuilder.anAddress().withNumber(100).build()
        var anotherAddress = AddressBuilder.anAddress().build()

        assertNotEquals(address, anotherAddress)
    }

    @Test
    fun testTwoBasicAddressWithDifferentStreetAddressAreNotEqualAddress(){
        var address = AddressBuilder.anAddress().withStreet("another street").build()
        var anotherAddress = AddressBuilder.anAddress().build()

        assertNotEquals(address, anotherAddress)
    }

    @Test
    fun testTwoBasicAddressWithDifferentLocalityAddressAreNotEqualAddress(){
        var address = AddressBuilder.anAddress().withLocality("another locality").build()
        var anotherAddress = AddressBuilder.anAddress().build()

        assertNotEquals(address, anotherAddress)
    }

    @Test
    fun testGivenAnAddressWithAGeographicZoneUNQReturnsDistanceToAnotherAddressWithZoneCONSTITheDiffIs12_74(){
        var unq = GeographicMapBuilder.aGeographicMap().withLatitude(-34.706272).withLongitude(-58.278519).build()
        var consti = GeographicMapBuilder.aGeographicMap().withLatitude(-34.628126).withLongitude(-58.380523).build()
        var address = AddressBuilder.anAddress().withZone(unq).build()

        var distance = address.calculateDistanceWithInKm(consti.latitude, consti.longitude)
        assertEquals(distance, 12.74)
    }

    @Test
    fun testGivenAnAddressWithAGeographicZoneUNQReturnsDistanceToAnotherAddressWithZoneOBELISCOTheDiffIs14_81(){
        var unq = GeographicMapBuilder.aGeographicMap().withLatitude(-34.706272).withLongitude(-58.278519).build()
        var obelisco = GeographicMapBuilder.aGeographicMap().withLatitude(-34.603595).withLongitude(-58.381717).build()
        var address = AddressBuilder.anAddress().withZone(unq).build()

        var distance = address.calculateDistanceWithInKm(obelisco.latitude, obelisco.longitude)
        assertEquals(distance, 14.81)
    }

    @Test
    fun testGivenAnAddressWithAGeographicZoneUNQReturnsDistanceToAnotherAddressWithZoneMarianoMorenoReturns0_68(){
        var unq = GeographicMapBuilder.aGeographicMap().withLatitude(-34.706272).withLongitude(-58.278519).build()
        var marianoMoreno = GeographicMapBuilder.aGeographicMap().withLatitude(-34.710895).withLongitude(-58.283421).build()
        var address = AddressBuilder.anAddress().withZone(unq).build()

        var distance = address.calculateDistanceWithInKm(marianoMoreno.latitude, marianoMoreno.longitude)
        assertEquals(distance, 0.68)
    }

    @Test
    fun testGivenAnAddressWithLocalityEmptyThrowInvalidLocalityAddressException(){
        assertFailsWith(InvalidLocalityAddressException::class){
            var address = AddressBuilder.anAddress().withLocality("").build()
            address.validated()
        }
    }

    @Test
    fun testGivenAnAddressWithLocalityWithSpecialCharactersThrowInvalidLocalityAddressException(){
        assertFailsWith(InvalidLocalityAddressException::class){
            var address = AddressBuilder.anAddress().withNumber(10).withLocality("'+").build()
            address.validated()
        }
    }

    @Test
    fun testGivenAnAddressWithNumberAddres0ThrowInvalidNumberAddressException(){
        assertFailsWith(InvalidNumberAddressException::class){
            var address = AddressBuilder.anAddress().withLocality("Quilmes").withNumber(0).build()
            address.validated()
        }
    }

    @Test
    fun testGivenAnAddressWithNegativeNumberAddresThrowInvalidNumberAddressException(){
        assertFailsWith(InvalidNumberAddressException::class){
            var address = AddressBuilder.anAddress().withLocality("Quilmes").withNumber(-1).build()
            address.validated()
        }
    }

    @Test
    fun testGivenAnAddressWithEmptyStreetThrowInvalidStreetAddressException(){
        assertFailsWith(InvalidStreetAddressException::class){
            var address = AddressBuilder.anAddress().withLocality("Quilmes").withNumber(10)
                    .withStreet("").build()
            address.validated()
        }
    }

    @Test
    fun testGivenAnAddressWithStreetWithSpecialCharactersThrowInvalidStreetAddressException(){
        assertFailsWith(InvalidStreetAddressException::class){
            var address = AddressBuilder.anAddress().withLocality("Quilmes")
                    .withNumber(10).withStreet("¿|").build()
            address.validated()
        }
    }
}