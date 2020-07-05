package unq.tpi.desapp.model.deliveryType

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import unq.tpi.desapp.builders.AddressBuilder
import kotlin.test.*
import unq.tpi.desapp.builders.HomeDeliveryBuilder
import unq.tpi.desapp.model.Address
import java.time.LocalDate
import java.time.LocalTime

class HomeDeliveryTest {

    @Test
    fun testHomeDeliveryTypeCreation(){
        var deliveryType = HomeDeliveryBuilder.aHomeDelivery().build()
        assertEquals(deliveryType.date, LocalDate.MIN)
        assertEquals(deliveryType.hourOfTheDelivery(), LocalTime.MIDNIGHT)
        assertEquals(deliveryType.addressOfPickup(), AddressBuilder.anAddress().build())
    }

    @Test
    fun testHomeDeliveryTypeWithDate10052020(){
        var date = LocalDate.of(2020, 10, 5)
        var deliveryType = HomeDeliveryBuilder.aHomeDelivery().withDate(date).build()

        assertEquals(deliveryType.dateOfTheDelivery(), date)
        assertEquals(deliveryType.addressOfPickup(), AddressBuilder.anAddress().build())
        assertEquals(deliveryType.hourOfTheDelivery(), LocalTime.MIDNIGHT)
    }

    @Test
    fun testHomeDeliveryTypeWithHour1530(){
        var hour = LocalTime.of(15, 30)
        var deliveryType = HomeDeliveryBuilder.aHomeDelivery().withHour(hour).build()

        assertEquals(deliveryType.dateOfTheDelivery(), LocalDate.MIN)
        assertEquals(deliveryType.addressOfPickup(), AddressBuilder.anAddress().build())
        assertEquals(deliveryType.hourOfTheDelivery(), hour)
    }

    @Test
    fun testHomeDeliveryTypeWithAddressCalleFalsa123(){
        var address = AddressBuilder.anAddress().build()
        var deliveryType = HomeDeliveryBuilder.aHomeDelivery().withAddress(address).build()

        assertEquals(deliveryType.dateOfTheDelivery(), LocalDate.MIN)
        assertEquals(deliveryType.hourOfTheDelivery(), LocalTime.MIDNIGHT)
        assertEquals(deliveryType.addressOfPickup(), address)
    }

    @Test
    fun testHomeDeliveryTypeWithDate10052020AndHour1530(){
        var date = LocalDate.of(2020, 10, 5)
        var hour = LocalTime.of(15, 30)
        var address = AddressBuilder.anAddress().build()
        var deliveryType = HomeDeliveryBuilder.aHomeDelivery().withDate(date)
                .withHour(hour).withAddress(address).build()

        assertEquals(deliveryType.dateOfTheDelivery(), date)
        assertEquals(deliveryType.hourOfTheDelivery(), hour)
        assertEquals(deliveryType.addressOfPickup(), address)
    }

    @Test
    fun testThrowCannotPickupException(){
        assertFailsWith(CannotPickUpException::class){
            var deliveryType = HomeDeliveryBuilder.aHomeDelivery().build()
            deliveryType.pickUpDateOfTheDelivery()
        }
    }

    @Test
    fun testEqualityHomeDeliveryToAnotherOneWithSameAttributes(){
        var deliveryTypeA = HomeDeliveryBuilder.aHomeDelivery().build()
        var deliveryTypeB = HomeDeliveryBuilder.aHomeDelivery().build()

        assertEquals(deliveryTypeA, deliveryTypeB)
    }

    @Test
    fun testEqualityHomeDeliveryToAnotherOneWithDifferentAddresses(){
        var address = AddressBuilder.anAddress().withStreet("test").build()
        var deliveryTypeA = HomeDeliveryBuilder.aHomeDelivery().build()
        var deliveryTypeB = HomeDeliveryBuilder.aHomeDelivery().withAddress(address).build()

        assertNotEquals(deliveryTypeA, deliveryTypeB)
    }

    @Test
    fun testEqualityHomeDeliveryToAnotherOneWithDifferentDate(){
        var date = LocalDate.MAX
        var deliveryTypeA = HomeDeliveryBuilder.aHomeDelivery().build()
        var deliveryTypeB = HomeDeliveryBuilder.aHomeDelivery().withDate(date).build()

        assertNotEquals(deliveryTypeA, deliveryTypeB)
    }

    @Test
    fun testEqualityHomeDeliveryToAnotherOneWithDifferentHour(){
        var hour = LocalTime.MAX
        var deliveryTypeA = HomeDeliveryBuilder.aHomeDelivery().build()
        var deliveryTypeB = HomeDeliveryBuilder.aHomeDelivery().withHour(hour).build()

        assertNotEquals(deliveryTypeA, deliveryTypeB)
    }

    @Test
    fun testEqualsAndHashCodeMethods(){
        var deliveryTypeA = HomeDeliveryBuilder.aHomeDelivery().build()

        assertEquals(deliveryTypeA, deliveryTypeA)
        assertEquals(deliveryTypeA.hashCode(), deliveryTypeA.hashCode())
    }
}