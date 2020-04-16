package unq.tpi.desapp.builders

import unq.tpi.desapp.Producto

class ProductoBuilder {

    var id: Long = 0
    var imagen:String =""
    var nombre: String = ""
    var precio: Double = 0.0
    var marca: String = ""

    companion object {
        @JvmStatic
        fun unProducto():ProductoBuilder{
            return ProductoBuilder()
        }
    }

    fun build(): Producto {
        return Producto(id, imagen, nombre, precio, marca)
    }

    fun conId(id:Long): ProductoBuilder{
        this.id = id
        return this
    }

    fun conUrl(url:String):ProductoBuilder{
        this.imagen = url
        return this
    }

    fun conMarca(marca:String):ProductoBuilder{
        this.marca = marca
        return this
    }

    fun conNombre(nombre:String):ProductoBuilder{
        this.nombre = nombre
        return this
    }

    fun conPrecio(precio:Double):ProductoBuilder{
        this.precio = precio
        return this
    }


}