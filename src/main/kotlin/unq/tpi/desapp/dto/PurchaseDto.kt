package unq.tpi.desapp.dto

import com.fasterxml.jackson.annotation.JsonCreator
import org.joda.time.DateTime
import unq.tpi.desapp.model.*
import unq.tpi.desapp.model.deliveryType.DeliveryType
import unq.tpi.desapp.model.deliveryType.HomeDelivery

data class PurchaseDto @JsonCreator constructor(
    var items:MutableList<ItemDto>,
    var user:UserDto,
    var deliveryType: DeliveryType,
    var paymentMethod: PaymentMethod
)