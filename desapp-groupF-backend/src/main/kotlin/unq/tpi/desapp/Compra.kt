package unq.tpi.desapp

import java.time.LocalDate

class Compra {
    var dateTime: LocalDate = TODO()
    var listaDeProductos: MutableList<ItemCompra> = mutableListOf()
    var usuario: Usuario = TODO()
    var tipoDeEnvio: String = ""
    //estado
    //itemCompra

    fun devolverTotal(): Double {
        return 0.0
    }

    fun agregarItem(item: ItemCompra) {

    }

}



