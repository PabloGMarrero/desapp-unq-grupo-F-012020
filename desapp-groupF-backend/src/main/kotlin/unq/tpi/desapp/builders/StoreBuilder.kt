package unq.tpi.desapp.builders

import unq.tpi.desapp.Discount
import unq.tpi.desapp.Product
import unq.tpi.desapp.Purchase
import unq.tpi.desapp.Store


class StoreBuilder {

    var activity: String = ""
    var adress: String = ""
    var coverageDistance: Double = 0.0
    var openingHours: MutableList<Store.OpenHours> = mutableListOf()
    var paymentTypes: MutableList<Store.PaymentType> = mutableListOf()
    var productList: MutableList<Product> =  mutableListOf()
    var discounts: MutableList<Discount> =  mutableListOf()
    var purchasesReceived: MutableList<Purchase> = mutableListOf()

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

    fun withAdress(url:String):StoreBuilder{
        this.adress = adress
        return this
    }

    fun withDistance(distance: Double):StoreBuilder{
        this.coverageDistance = distance
        return this
    }


}