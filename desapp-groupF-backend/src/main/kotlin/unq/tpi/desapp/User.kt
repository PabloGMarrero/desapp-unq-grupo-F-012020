package unq.tpi.desapp

class User(aName: String, aPassword: String, aMail: String) {

    var name: String = aName
    var password: String = aPassword
    var email: String = aMail
    var purchaseRange: Double = 0.0
    var categoryPreferences: MutableList<Categories> =  mutableListOf()
    var historialPurchases: MutableList<Purchase> =  mutableListOf()
    var shoppingBag: MutableList<Item> = mutableListOf()

    class Categories(name: String) {
        var name: String = name

    }

    fun adddItemProduct(aItem: Item) {
        shoppingBag.add(aItem)
    }

    fun addToHistorial(purchase: Purchase){
        historialPurchases.add(purchase)
    }

    fun changePurchaseRange(distancia: Double){
        this.purchaseRange = distancia
    }

    fun addCategory(aCategory: Categories){
        this.categoryPreferences.add(aCategory)
    }


}

