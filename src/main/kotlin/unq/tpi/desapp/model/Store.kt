package unq.tpi.desapp.model

import java.io.Serializable
import javax.persistence.*
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

/**
 * Store represent the store business to each owner.
 *
 * @param activity represents what is the specific business.
 * @param address represents the address of the store.
 * @param distance represents what is his max distance to cover in the map.
 */


@Entity
@Table(name="stores")
class Store : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column
    var activity: String = ""

    @Column
    var address: String = ""

    @Column
    var coverageDistance: Double = 0.0

    @Transient
    var openingHours: MutableList<OpenHours> = mutableListOf()

    @Transient
    var paymentsMethods: MutableList<PaymentMethod> = mutableListOf()

    @Transient
    var productList: MutableList<Product> =  mutableListOf()

    @Transient
    var discounts: MutableList<Discount> =  mutableListOf()

    @Transient
    var purchasesReceived: MutableList<Purchase> = mutableListOf()

    init {
        paymentsMethods.add(PaymentMethod.CASH)
    }


    constructor()
    constructor(storeId:Long,activity: String, address: String, distance: Double ){
        this.id = storeId
        this.activity = activity
        this.address = address
        this.coverageDistance = distance
    }


    /**
     * Adds a new [discount] to the store.
     * @param discount
     */
    fun addDiscount(discount : Discount) {
        this.discounts.add(discount)
    }

    /**
     * Adds a new [product] to the store.
     * @param product
     */
    fun addProduct(product: Product) {
        this.productList.add(product)
    }

    /**
     * To know if the product is on the stock
     * @param product to know if already has on his stock
     * @return true if has already the product on his stock
     */
    fun hasProductAsStock(product: Product): Boolean {
        return this.productList.contains(product)
    }

    /**
     * Adds a new [purchase] to the store.
     * @param purchase
     */
    fun addPurchase (purchase: Purchase) {
        this.purchasesReceived.add(purchase)
    }

    /**
     * Adds the [openHours] of the store.
     * @param openHours
     */
    fun addOpenhours(openHours: OpenHours){
        this.openingHours.add(openHours)
    }

    /**
     * Change the coverage distance of the store
     * @param newDistance represents the new distance to cover
     */
    fun changeCoverageDistance(newDistance: Double){
        this.coverageDistance = newDistance
    }


    /**
     * Adds a [paymentMethod] to the itemlist of the purchase.
     * @param paymentMethod represents one item of the purcase
     */
    fun addPaymentMethod(paymentMethod: PaymentMethod) {
        if (! this.paymentsMethods.contains(paymentMethod)){
            this.paymentsMethods.add(paymentMethod)
        }
    }

    /**
     * Adds a [item] to the itemlist of the purchase.
     * @param item represents one item of the purcase
     */
    fun deletePaymentMethod(paymentMethod: PaymentMethod) {
        this.paymentsMethods.remove(paymentMethod)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Store

        if (address != other.address) return false

        return true
    }

    override fun hashCode(): Int {
        return address.hashCode()
    }


}