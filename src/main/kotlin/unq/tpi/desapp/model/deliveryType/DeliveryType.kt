package unq.tpi.desapp.model.deliveryType

import java.time.LocalDate
import java.time.LocalTime

/**
 * DeliveryType is the interface class to define the protocol of the delivery types
 */
interface DeliveryType {

    fun hourOfTheDelivery():LocalTime
    fun dateOfTheDelivery():LocalDate
    fun addressOfPickup():String
    fun pickUpDateOfTheDelivery():LocalDate
}