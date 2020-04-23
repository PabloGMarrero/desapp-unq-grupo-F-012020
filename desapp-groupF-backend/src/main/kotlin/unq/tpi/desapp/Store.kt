package unq.tpi.desapp

/**
 * Store represent the store business to each owner.
 *
 * @param activity represents what is the specific business.
 * @param address represents the address of the store.
 * @param distance represents what is his max distance to cover in the map.
 */

class Store(activity: String, address: String, distance: Double )  {

    var activity: String = activity
    var address: String = address
    var coverageDistance: Double = distance
    var openingHours: MutableList<OpenHours> = mutableListOf()
    var paymentTypes: MutableList<PaymentType> = mutableListOf()
    var productList: MutableList<Product> =  mutableListOf()
    var discounts: MutableList<Discount> =  mutableListOf()
    var purchasesReceived: MutableList<Purchase> = mutableListOf()

    class PaymentType(bank:String, card: String){
        var bank: String = bank
        var card: String = card
    }

    class OpenHours(day:String, hourFrom:String, hourTo:String){
        var day: String = day
        var hourFrom: String = hourFrom
        var hourTo: String = hourTo
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
     * Adds a new [pay] to the store.
     * @param pay
     */
    fun addPaymentType (pay: PaymentType){
        this.paymentTypes.add(pay)
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


}