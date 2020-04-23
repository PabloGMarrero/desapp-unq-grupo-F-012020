package unq.tpi.desapp

import java.time.LocalDate

class Purchase(aUser: User, deliveryType: String) {
    var purchaseDate: LocalDate = LocalDate.now()
    var itemList: MutableList<Item> = mutableListOf()
    var user: User = aUser
    var deliveryType: String = deliveryType
    var status: String = ""

    fun getTotal(): Double {
        var total = 0.0
        for (item in itemList){
            total = total + (item.quantity * item.product.price)
        }
        return total
    }

    fun addItem(item: Item) {
        this.itemList.add(item)

    }

    fun changeStatus( newState: String){
        this.status = newState
    }

}



