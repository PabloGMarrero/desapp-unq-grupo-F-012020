package unq.tpi.desapp.model

import com.fasterxml.jackson.annotation.JsonIgnore
import unq.tpi.desapp.builders.HomeDeliveryBuilder
import unq.tpi.desapp.builders.UserBuilder
import unq.tpi.desapp.model.deliveryType.DeliveryType
import unq.tpi.desapp.model.deliveryType.HomeDelivery
import java.time.LocalDate
import javax.persistence.*
import kotlin.jvm.Transient

/**
 * Purchase represents the purchase of each user
 *
 * @param anUser represents the user what did the purchase.
 * @param deliveryType represents the type of delivery chosen
 * @param paymentMethod represents the payment method choosen by the user
 */
@Entity
@Table(name="purchase")
class Purchase{
    constructor(anUser: User, deliveryType: DeliveryType, paymentMethod: PaymentMethod){
        //this.user = anUser
        this.deliveryType = deliveryType
        this.paymentMethod = paymentMethod
    }
    constructor()

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = 0

    @Column
    var purchaseDate: LocalDate = LocalDate.now()

    @OneToMany
    @JoinColumn(name = "item_id")
    var itemList: MutableList<Item> = mutableListOf()

    @JsonIgnore
    @Transient
    var user: User = UserBuilder.anUser().build()

    @Transient
    @OneToOne(cascade = [CascadeType.ALL])
    var deliveryType: DeliveryType = HomeDeliveryBuilder.aHomeDelivery().build()

    @Enumerated(value = EnumType.STRING)
    var paymentMethod: PaymentMethod = PaymentMethod.CASH

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
    fun changeDeliveryType( aDeliverytype: HomeDelivery){
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



