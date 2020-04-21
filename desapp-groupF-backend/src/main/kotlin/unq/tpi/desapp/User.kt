package unq.tpi.desapp

class User(nombre: String, contraseña: String, email: String) {

    var nombre: String = ""
    var contrasenia: String = ""
    var email: String = ""
    var purchaseRange: Double = 0.0
    var categoryPreferences: MutableList<Categories> =  mutableListOf()
    var historialPurchases: MutableList<Purchase> =  mutableListOf()
    var shoppingBag: MutableList<Item> = mutableListOf()

    class Categories(name: String) {
        var name: String = name

    }

    fun adddItemProduct(product: Product, quantity: Double, store: Store) {
        shoppingBag.add(Item(product,quantity, store))
    }

    fun addToAlHistorial(purchase: Purchase){
        historialPurchases.add(purchase)
    }

    fun changePurchaseRange(distancia: Double){
        this.purchaseRange = distancia
    }


}

