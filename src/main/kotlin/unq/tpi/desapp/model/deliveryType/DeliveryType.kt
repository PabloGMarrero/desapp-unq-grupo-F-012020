package unq.tpi.desapp.model.deliveryType

import java.time.LocalDate
import java.time.LocalTime

/**
 * DeliveryType is the interface class to define the protocol of the delivery types
 */
interface DeliveryType {

    /**
     * Returnn the hour of the delivery
     */
    fun hourOfTheDelivery():LocalTime

    /**
     * Returnn the date of the delivery
     */
    fun dateOfTheDelivery():LocalDate

    /**
     * Returnn the address of the delivery
     */
    fun addressOfPickup():String

    /**
     * Returnn the pickup date of the delivery
     */
    fun pickUpDateOfTheDelivery():LocalDate
}