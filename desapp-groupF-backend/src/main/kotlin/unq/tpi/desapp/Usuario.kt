package unq.tpi.desapp

class Usuario {

    var nombre: String = ""
    var contrasenia: String = ""
    var email: String = ""
    var umbralDeCompra: Double = 0.0
    var preferenciaCategoria: MutableList<Categorias> =  mutableListOf()
    var historialCompras: MutableList<Compra> =  mutableListOf()
    var carrito: MutableList<ItemCompra> = mutableListOf()

    class Categorias {

    }
}

