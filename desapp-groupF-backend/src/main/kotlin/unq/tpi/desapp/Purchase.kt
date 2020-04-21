package unq.tpi.desapp

import java.time.LocalDate

class Purchase {
    var dateTime: LocalDate = TODO()
    var listaDeProductos: MutableList<Item> = mutableListOf()
    var user: User = TODO()
    var tipoDeEnvio: String = ""
    //estado
    //itemCompra

    fun devolverTotal(): Double {
        return 0.0
    }

    fun agregarItem(item: Item) {

    }

}



