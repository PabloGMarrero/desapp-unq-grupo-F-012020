package unq.tpi.desapp.dto

import com.fasterxml.jackson.annotation.JsonCreator
import org.joda.time.DateTime
import unq.tpi.desapp.model.Address
import unq.tpi.desapp.model.PaymentMethod
import unq.tpi.desapp.model.Product
import unq.tpi.desapp.model.User
import unq.tpi.desapp.model.deliveryType.DeliveryType

data class PurchaseDto @JsonCreator constructor(
    var products:MutableList<Product>,
    var user:User,
    var deliveryType: DeliveryType,
    var paymentMethod: PaymentMethod,
    var address: Address
)