package unq.tpi.desapp.model.deliveryType

import com.fasterxml.jackson.annotation.JsonTypeName
import java.time.LocalDate
import java.time.LocalTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * Pickup in store represent the idea of pick up the purchase on the store.
 *
 * @param date represents the specific date of the pickup.
 * @param hour represents the specific hour of the pickup.
 * @param address represents the address where the user want to send the purchase
 */
@Entity
@JsonTypeName("HomeDelivery")
class HomeDelivery(date: LocalDate, hour: LocalTime, address:String): DeliveryType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = 0

    var date = date
    var hour = hour
    var address = address

    constructor():this(LocalDate.MIN, LocalTime.MAX, "")

    override fun hourOfTheDelivery(): LocalTime {
        return hour
    }

    override fun dateOfTheDelivery(): LocalDate {
        return date
    }

    override fun addressOfPickup(): String {
        return address
    }

    override fun pickUpDateOfTheDelivery(): LocalDate {
        return throw CannotPickUpException("You can not pickup the purchase. Change delivery mode to Pickup in store.")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HomeDelivery

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

