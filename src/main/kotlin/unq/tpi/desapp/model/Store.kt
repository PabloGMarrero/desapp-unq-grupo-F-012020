package unq.tpi.desapp.model

import unq.tpi.desapp.builders.AddressBuilder
import unq.tpi.desapp.exceptions.InvalidCoverageDistanceStoreException
import javax.persistence.*
import kotlin.jvm.Transient

/**
 * Store represent the store business to each owner.
 *
 * @param activity represents what is the specific business.
 * @param address represents the address of the store.
 * @param distance represents what is his max distance to cover in the map.
 */


@Entity
@Table(name="stores")
class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column
    var activity: String = ""

    @Column
    var storeName: String = ""

    @Column
    var address: Address = AddressBuilder.anAddress().build()

    @Column
    var coverageDistance: Double = 0.0

    @Transient
    var openingHours: MutableList<OpenHours> = mutableListOf()

    @Transient
    var paymentsMethods: MutableList<PaymentMethod> = mutableListOf()

    @OneToMany
    @JoinColumn(name="store_id")
    var productList: MutableList<Product> =  mutableListOf()

    @Transient
    var discounts: MutableList<Discount> =  mutableListOf()

    @Transient
    var purchasesReceived: MutableList<Purchase> = mutableListOf()

    @Transient
    var listOfTurns: MutableList<Turn> = mutableListOf()



    init {
        paymentsMethods.add(PaymentMethod.CASH)
    }

    constructor()
    constructor(storeId:Long, activity: String, address: Address, distance: Double, storeName:String){
        this.id = storeId
        this.activity = activity
        this.address = address
        this.coverageDistance = distance
        this.storeName = storeName
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
     * Adds a [paymentMethod] to the payments methods list.
     * @param paymentMethod represents one payment method
     */
    fun addPaymentMethod(paymentMethod: PaymentMethod) {
        if (! this.paymentsMethods.contains(paymentMethod)){
            this.paymentsMethods.add(paymentMethod)
        }
    }

    /**
     * Delete the [paymentMethod] from the payments methods list.
     * @param paymentsMethods
     */
    fun deletePaymentMethod(paymentMethod: PaymentMethod) {
        this.paymentsMethods.remove(paymentMethod)
    }

    /**
     *
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Store

        if (address != other.address) return false

        return true
    }

    /**
     *
     */
    override fun hashCode(): Int {
        return address.hashCode()
    }

    /**
     * Adds a [aTurn] to the listOfTurns of the store.
     * @param aTurn
     */
    fun addTurn(aTurn: Turn){
        this.listOfTurns.add(aTurn)
    }

    /**
     * Adds a [aTurn] to the listOfTurns of the store.
     * @param aTurn
     */
    fun deleteTurn(aTurn: Turn){
        this.listOfTurns.remove(aTurn)
    }

    /**
     * Returns the name of the street of the address
     * @return an string that represents the name of the street
     */
    fun getAddressStreet():String{
        return address.street
    }

    /**
     * Returns the number of the street of the address
     * @return a number that represents the name of the street
     */
    fun getAddressNumber():Long{
        return address.number
    }

    /**
     * Returns true if the store is isnide of the latitude and longitude
     * @param lat represents the latitude
     * @param lon represents the longitude
     * @return boolean if the store is inside of the range.
     */

    fun isInsideRange(lat:Double, lon: Double):Boolean{
        var distance = this.address.calculateDistanceWithInKm(lat, lon)
        return distance <= this.coverageDistance
    }


    /**
     * Validates if the store is well done.
     */
    fun validated() {
        if (this.coverageDistance <= 0.5){
            throw InvalidCoverageDistanceStoreException("The distance can not be less than 0.")
        }
    }
}