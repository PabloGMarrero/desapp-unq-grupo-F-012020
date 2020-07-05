package unq.tpi.desapp.model.deliveryType

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import unq.tpi.desapp.model.Address
import java.time.LocalDate
import java.time.LocalTime
import javax.persistence.Entity

/**
 * DeliveryType is the interface class to define the protocol of the delivery types
 */

@JsonTypeInfo (
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY ,
        property = "type")
//@JsonSubTypes(
//    Type(value = HomeDelivery::class, name = "HomeDelivery"),
//    Type(value = PickupInStore::class, name = "PickupInStore")
//)
@JsonSubTypes(
        JsonSubTypes.Type(name = "HomeDelivery", value = HomeDelivery::class),
        JsonSubTypes.Type(value = PickupInStore::class, name = "PickupInStore")
)
interface DeliveryType {
    /**
     * Returnn the hour of the delivery
     */
    @JsonIgnore
    fun hourOfTheDelivery():LocalTime

    /**
     * Returnn the date of the delivery
     */
    @JsonIgnore
    fun dateOfTheDelivery():LocalDate

    /**
     * Returnn the address of the delivery
     */
    @JsonIgnore
    fun addressOfPickup():Address

    /**
     * Returnn the pickup date of the delivery
     */
    @JsonIgnore
    fun pickUpDateOfTheDelivery():LocalDate
}