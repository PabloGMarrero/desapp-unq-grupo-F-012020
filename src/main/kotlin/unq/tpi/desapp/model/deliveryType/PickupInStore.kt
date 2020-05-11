package unq.tpi.desapp.model.deliveryType

import unq.tpi.desapp.model.deliveryType.DeliveryType
import java.time.LocalTime
import java.time.LocalDate

/**
 * Pickup in store represent the idea of pick up the purchase on the store.
 *
 * @param date represents the specific date of the pickup.
 * @param hour represents the specific hour of the pickup.
 * @param storeAddress represents the address of the store where the purchase was done
  */
class PickupInStore(date:LocalDate, hour: LocalTime, storeAddress:String): DeliveryType {
    var date = date
    var hour = hour
    var storeAddress = storeAddress

    override fun hourOfTheDelivery():LocalTime{
        return  hour
    }

    override fun dateOfTheDelivery():LocalDate{
        return date
    }

    override fun addressOfPickup():String{
        return storeAddress
    }

    override fun pickUpDateOfTheDelivery():LocalDate{
        return this.dateOfTheDelivery()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PickupInStore

        if (date != other.date) return false
        if (hour != other.hour) return false
        if (storeAddress != other.storeAddress) return false

        return true
    }

    override fun hashCode(): Int {
        var result = date.hashCode()
        result = 31 * result + hour.hashCode()
        result = 31 * result + storeAddress.hashCode()
        return result
    }
}
