package unq.tpi.desapp

class Store(activity: String, adress: String, distance: Double )  {

    var activity: String = activity
    var adress: String = adress
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

    fun addDiscount(discount : Discount) {
        this.discounts.add(discount)
    }

    fun addProduct(product: Product) {
        this.productList.add(product)
    }

    fun Stock(product: Product): Boolean {
        return this.productList.contains(product)

    }

    fun addPurchase (purchase: Purchase) {
        this.purchasesReceived.add(purchase)
    }

    fun addPaymentType (pay: PaymentType){
        this.paymentTypes.add(pay)
    }

    fun addOpenhours(openHours: OpenHours){
        this.openingHours.add(openHours)
    }

    fun changeCoverageDistance(newDistance: Double){
        this.coverageDistance = newDistance
    }



}
//e2a1d8454e4948008593996ff0f8af46 token repository