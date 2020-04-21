package unq.tpi.desapp

class User(nombre: String, contraseña: String, email: String) {

    var nombre: String = ""
    var contrasenia: String = ""
    var email: String = ""
    var umbralDeCompra: Double = 0.0
    var preferenciaCategoria: MutableList<Categorias> =  mutableListOf()
    var historialPurchases: MutableList<Purchase> =  mutableListOf()
    var carrito: MutableList<Item> = mutableListOf()

    class Categorias {

    }

    fun agregarItemProducto(product: Product, cantidad: Double, store: Store) {
        carrito.add(Item(product,cantidad, store))
    }

    fun agregarAlHistorial(purchase: Purchase){
        historialPurchases.add(purchase)
    }

    fun cambiarUmbralDeCompra(distancia: Double){
        this.umbralDeCompra = distancia
    }


}

