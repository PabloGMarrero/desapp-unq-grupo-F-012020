package unq.tpi.desapp.model

import unq.tpi.desapp.model.deliveryType.DeliveryType
import java.time.LocalDate

/**
 * Purchase represents the purchase of each user
 *
 * @param anUser represents the user what did the purchase.
 * @param deliveryType represents the type of delivery chosen
 */
class Purchase(anUser: User, deliveryType: DeliveryType, paymentMethod: PaymentMethod) {
    var purchaseDate: LocalDate = LocalDate.now()
    var itemList: MutableList<Item> = mutableListOf()
    var user: User = anUser
    var deliveryType: DeliveryType = deliveryType
    var paymentMethod: PaymentMethod = paymentMethod


    /**
     * Gets all the items of the purchase
     * @return the list of purchase items
     */
    fun getItems(): MutableList<Item> {
        return itemList
    }

    /**
     * Gets the total of the purchase
     * @return the sum of total price of products
     */
    fun getTotal(): Double {
        var total = 0.0
        for (item in itemList){
            total = total + (item.quantity * item.product.price)
        }
        return total
    }

    /**
     * Adds a [item] to the itemlist of the purchase.
     * @param item represents one item of the purcase
     */
    fun addItem(item: Item) {
        this.itemList.add(item)
    }

    /**
     * Change the type of delivery used in the purchase
     * @param newState the new type of delivery
     */
    fun changeDeliveryType( aDeliverytype: DeliveryType){
        this.deliveryType = aDeliverytype
    }


    /**
     * Change the paymment method of the purchase
     * @param aPaymentMethod the paymment method of the purchase
     */
    fun changePaymentMethod(aPaymentMethod: PaymentMethod){
        this.paymentMethod = aPaymentMethod
    }


}



