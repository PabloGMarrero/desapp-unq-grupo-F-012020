package unq.tpi.desapp.builders

import unq.tpi.desapp.*
import java.time.LocalDate

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

    fun build(): Product {
        return Product(id, imagen, nombre, precio, marca)
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

class StoreBuilder {

    var activity: String = ""
    var adress: String = ""
    var coverageDistance: Double = 0.0

    companion object {
        @JvmStatic
        fun aStore():StoreBuilder{
            return StoreBuilder()
        }
    }

    fun build(): Store {
        return Store(activity, adress, coverageDistance)
    }

    fun withActivity(activity: String): StoreBuilder{
        this.activity = activity
        return this
    }

    fun withAdress(adress :String):StoreBuilder{
        this.adress = adress
        return this
    }

    fun withDistance(distance: Double):StoreBuilder{
        this.coverageDistance = distance
        return this
    }



}

class PurchaseBuilder {

    var user: User = User("","","")
    var deliveryType: String = ""


    companion object {
        @JvmStatic
        fun aPurchase():PurchaseBuilder{
            return PurchaseBuilder()
        }
    }

    fun build(): Purchase {
        return Purchase(user, deliveryType)
    }


}

class UserBuilder {

    var name: String = ""
    var pass: String = ""
    var email: String = ""
    var purchaseRange: Double = 0.0


    companion object {
        @JvmStatic
        fun aUser():UserBuilder{
            return UserBuilder()
        }
    }

    fun build(): User {
        return User(name,pass,email)
    }


}