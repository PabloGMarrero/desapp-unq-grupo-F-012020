package unq.tpi.desapp

class Usuario(nombre: String, contraseña: String, email: String) {

    var nombre: String = ""
    var contrasenia: String = ""
    var email: String = ""
    var umbralDeCompra: Double = 0.0
    var preferenciaCategoria: MutableList<Categorias> =  mutableListOf()
    var historialCompras: MutableList<Compra> =  mutableListOf()
    var carrito: MutableList<ItemCompra> = mutableListOf()

    class Categorias {

    }

    fun agregarItemProducto(producto: Producto, cantidad: Double) {
        carrito.add(ItemCompra(producto,cantidad))
    }

    fun agregarAlHistorial(compra: Compra){
        historialCompras.add(compra)
    }

    fun cambiarUmbralDeCompra(distancia: Double){
        this.umbralDeCompra = distancia
    }


}

