package unq.tpi.desapp.model.deliveryType

import org.junit.jupiter.api.Assertions.*
import org.springframework.boot.test.context.SpringBootTest
import org.junit.jupiter.api.Test
import unq.tpi.desapp.builders.PickupInStoreBuilder
import java.time.LocalDate
import java.time.LocalTime

@SpringBootTest
class PickupInStoreTest {

    @Test
    fun testPickUpInSoreDeliveryModeCreation(){
        var deliveryType = PickupInStoreBuilder.aPickupInStore().build()
        assertEquals(deliveryType.date, LocalDate.MIN)
        assertEquals(deliveryType.hour, LocalTime.MIDNIGHT)
        assertEquals(deliveryType.storeAddress, "")
    }

    @Test
    fun testPickUpInSoreDeliveryModeWithDate10052020(){
        var date = LocalDate.of(2020, 10, 5)
        var deliveryType = PickupInStoreBuilder.aPickupInStore().withDate(date).build()

        assertEquals(deliveryType.date, date)
        assertEquals(deliveryType.addressOfPickup(), "")
        assertEquals(deliveryType.hour, LocalTime.MIDNIGHT)
    }

    @Test
    fun testPickUpInSoreDeliveryModeWithHour1530(){
        var hour = LocalTime.of(15, 30)
        var deliveryType = PickupInStoreBuilder.aPickupInStore().withHour(hour).build()

        assertEquals(deliveryType.dateOfTheDelivery(), LocalDate.MIN)
        assertEquals(deliveryType.hourOfTheDelivery(), hour)
        assertEquals(deliveryType.addressOfPickup(), "")
    }

    @Test
    fun testPickUpInSoreDeliveryModeWithAddressCalleFalsa123(){
        var address = "Calle Falsa 123"
        var deliveryType = PickupInStoreBuilder.aPickupInStore().withStoreAddress(address).build()

        assertEquals(deliveryType.dateOfTheDelivery(), LocalDate.MIN)
        assertEquals(deliveryType.hourOfTheDelivery(), LocalTime.MIDNIGHT)
        assertEquals(deliveryType.addressOfPickup(), address)
    }

    @Test
    fun testPickUpInSoreDeliveryModeWithDate10052020AndHour1530(){
        var date = LocalDate.of(2020, 10, 5)
        var hour = LocalTime.of(15, 30)
        var address = "Calle Falsa 123"
        var deliveryType = PickupInStoreBuilder.aPickupInStore().withDate(date)
                .withHour(hour).withStoreAddress(address).build()

        assertEquals(deliveryType.dateOfTheDelivery(), date)
        assertEquals(deliveryType.hourOfTheDelivery(), hour)
        assertEquals(deliveryType.addressOfPickup(), address)
    }

    @Test
    fun testDontThrowCannotPickupExceptionJustReturnTheDate(){
        var date = LocalDate.of(2020, 10, 5)
        var deliveryType = PickupInStoreBuilder.aPickupInStore().withDate(date).build()

        assertEquals(deliveryType.pickUpDateOfTheDelivery(), date)
    }

    @Test
    fun testEqualityHomeDeliveryToAnotherOneWithDifferentAddresses(){
        var deliveryTypeA = PickupInStoreBuilder.aPickupInStore().build()
        var deliveryTypeB = PickupInStoreBuilder.aPickupInStore().withStoreAddress("test").build()

        assertNotEquals(deliveryTypeA, deliveryTypeB)
    }

    @Test
    fun testEqualityHomeDeliveryToAnotherOneWithDifferentDate(){
        var date = LocalDate.MAX
        var deliveryTypeA = PickupInStoreBuilder.aPickupInStore().build()
        var deliveryTypeB = PickupInStoreBuilder.aPickupInStore().withDate(date).build()

        assertNotEquals(deliveryTypeA, deliveryTypeB)
    }

    @Test
    fun testEqualityHomeDeliveryToAnotherOneWithDifferentHour(){
        var hour = LocalTime.MAX
        var deliveryTypeA = PickupInStoreBuilder.aPickupInStore().build()
        var deliveryTypeB = PickupInStoreBuilder.aPickupInStore().withHour(hour).build()

        assertNotEquals(deliveryTypeA, deliveryTypeB)
    }

}