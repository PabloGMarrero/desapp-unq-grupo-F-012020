package unq.tpi.desapp.model.deliveryType

import com.fasterxml.jackson.annotation.JsonTypeName
import java.time.LocalTime
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * Pickup in store represent the idea of pick up the purchase on the store.
 *
 * @param date represents the specific date of the pickup.
 * @param hour represents the specific hour of the pickup.
 * @param address represents the address of the store where the purchase was done
  */

@Entity
@JsonTypeName("PickupInStore")
class PickupInStore(date:LocalDate, hour: LocalTime, storeAddress:String): DeliveryType {
    var date = date
    var hour = hour
    var address = storeAddress

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = 0

    override fun hourOfTheDelivery():LocalTime{
        return  hour
    }

    override fun dateOfTheDelivery():LocalDate{
        return date
    }

    override fun addressOfPickup():String{
        return address
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
        if (address != other.address) return false

        return true
    }

    override fun hashCode(): Int {
        var result = date.hashCode()
        result = 31 * result + hour.hashCode()
        result = 31 * result + address.hashCode()
        return result
    }
}
