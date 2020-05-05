package unq.tpi.desapp.model

import java.time.LocalDate

/**
 * Purchase represents the purchase of each user
 *
 * @param aUser represents the user what did the purchase.
 * @param deliveryType represents the type of delivery chosen
 */
class Purchase(aUser: User, deliveryType: String) {
    var purchaseDate: LocalDate = LocalDate.now()
    var itemList: MutableList<Item> = mutableListOf()
    var user: User = aUser
    var deliveryType: String = deliveryType
    var status: String = ""

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
     * Adds a [item] to the itemlist.
     * @param item
     */
    fun addItem(item: Item) {
        this.itemList.add(item)
    }

    /**
     * Change the type of delivery used in the purchase
     * @param newState the new type of delivery
     */
    fun changeStatus( newState: String){
        this.status = newState
    }

}



