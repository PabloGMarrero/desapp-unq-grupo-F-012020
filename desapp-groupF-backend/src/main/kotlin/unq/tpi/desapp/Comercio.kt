package unq.tpi.desapp

import java.time.LocalDate

class Comercio  {
    var rubro: String = ""
    var domicilio: String = ""
    // dias y horarios y atencion
    //medios de pago
    var distanciaMaxima: Double = 0.0
    var listaDeProductos: MutableList<Producto> =  mutableListOf()
    var ofertas: MutableList<Oferta> =  mutableListOf()
    var comprasRecibidas: MutableList<Compra> = mutableListOf()


    fun devolverListaDeProductos() : MutableList<Producto> {
        return listaDeProductos
    }

    fun agregarOferta(oferta : Oferta) {
        this.ofertas.add(oferta)
    }

    fun agregarProducto(producto: Producto) {
        this.listaDeProductos.add(producto)
    }

    fun tieneStock(producto: Producto): Boolean {
        return this.listaDeProductos.contains(producto)

    }

}
//e2a1d8454e4948008593996ff0f8af46 token repository