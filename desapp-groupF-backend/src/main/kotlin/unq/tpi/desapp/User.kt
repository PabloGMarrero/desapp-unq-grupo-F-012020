package unq.tpi.desapp

/**
 * Purchase represents the purchase of each user
 *
 * @param aName represents the name of the User.
 * @param aPassword represents the password
 * @param aMail represents the email of the user.
 */
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

    /**
     * Add a new item to the bag
     * @param aItem
     */
    fun addItemProduct(aItem: Item) {
        shoppingBag.add(aItem)
    }

    /**
     * Add the purchase to his own historial
     */
    fun addToHistorial(purchase: Purchase){
        historialPurchases.add(purchase)
    }

    /**
     * Changes the range of the purchase to find new stores in the map
     */
    fun changePurchaseRange(distancia: Double){
        this.purchaseRange = distancia
    }

    /**
     * Add a new category to his own preferences.
     */
    fun addCategory(aCategory: Categories){
        this.categoryPreferences.add(aCategory)
    }


}

