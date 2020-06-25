package unq.tpi.desapp.builders

import unq.tpi.desapp.model.*
import unq.tpi.desapp.model.deliveryType.DeliveryType
import unq.tpi.desapp.model.deliveryType.HomeDelivery
import unq.tpi.desapp.model.deliveryType.PickupInStore
import java.time.LocalDate
import java.time.LocalTime

class ProductBuilder {

    var id: Long = 0
    var urlImage:String =""
    var name: String = ""
    var price: Double = 0.0
    var brand: String = ""

    companion object {
        @JvmStatic
        fun aProduct():ProductBuilder{
            return ProductBuilder()
        }
    }

    fun build(): Product {
        return Product(id, urlImage, name, price, brand)
    }

    fun withId(id:Long): ProductBuilder{
        this.id = id
        return this
    }

    fun withUrl(url:String):ProductBuilder{
        this.urlImage = url
        return this
    }

    fun withBrand(brand:String):ProductBuilder{
        this.brand = brand
        return this
    }

    fun withName(name:String):ProductBuilder{
        this.name = name
        return this
    }

    fun withPrice(price:Double):ProductBuilder{
        this.price = price
        return this
    }


}

class StoreBuilder {

    var id: Long = 0
    var activity: String = ""
    var address: Address = AddressBuilder.anAddress().build()
    var coverageDistance: Double = 0.0
    var name = ""

    companion object {
        @JvmStatic
        fun aStore():StoreBuilder{
            return StoreBuilder()
        }
    }

    fun build(): Store {
        return Store(id, activity, address, coverageDistance, name)
    }

    fun withActivity(activity: String): StoreBuilder{
        this.activity = activity
        return this
    }

    fun withAdress(address : Address):StoreBuilder{
        this.address = address
        return this
    }

    fun withDistance(distance: Double):StoreBuilder{
        this.coverageDistance = distance
        return this
    }

    fun withStoreName(name:String ):StoreBuilder{
        this.name = name
        return this
    }
}

class ItemBuilder{
    var product: Product = ProductBuilder.aProduct().build()
    var quantity: Double = 0.00
    var store: Store = StoreBuilder.aStore().build()

    companion object{
        @JvmStatic
        fun anItem():ItemBuilder{
            return ItemBuilder()
        }
    }

    fun build(): Item {
        return Item(product, quantity, store)
    }

    fun withProduct(aProduct: Product):ItemBuilder{
        this.product = aProduct
        return this
    }

    fun withQuantity(aQuantity:Double):ItemBuilder{
        this.quantity = aQuantity
        return this
    }

    fun withAStore(aStore: Store):ItemBuilder{
        this.store = aStore
        return this
    }
}

class PurchaseBuilder {

    var user: User = UserBuilder.anUser().build()
    var deliveryType: DeliveryType = PickupInStoreBuilder.aPickupInStore().build()
    var paymentMethod: PaymentMethod = PaymentMethod.CASH

    companion object {
        @JvmStatic
        fun aPurchase():PurchaseBuilder{
            return PurchaseBuilder()
        }
    }

    fun build(): Purchase {
        return Purchase(user, deliveryType, paymentMethod)
    }

    fun withDeliveryType(aDeliveryType: DeliveryType):PurchaseBuilder{
        this.deliveryType = aDeliveryType
        return this
    }

    fun withUser(anUser: User):PurchaseBuilder{
        this.user = anUser
        return this
    }


}

class UserBuilder {

    var name: String = ""
    var pass: String = ""
    var email: String = ""
    var purchaseRange: Double = 0.0


    companion object {
        @JvmStatic
        fun anUser():UserBuilder{
            return UserBuilder()
        }
    }

    fun build(): User {
        return User(name, email, pass)
    }

    fun withName(name: String):UserBuilder{
        this.name = name
        return this
    }

    fun withEmail(email:String):UserBuilder{
        this.email = email
        return this
    }

    fun withPass(password:String):UserBuilder{
        this.pass = password
        return  this
    }

}


class DiscountByCategoryBuilder{
    var percentage: Double = 0.00
    var dateFrom: LocalDate = LocalDate.MIN
    var dateTo: LocalDate = LocalDate.MIN
    var categoryName:String =""

    companion object {
        @JvmStatic
        fun aDiscount():DiscountByCategoryBuilder{
            return DiscountByCategoryBuilder()
        }
    }

    fun withPercentage(percentage:Double):DiscountByCategoryBuilder{
        this.percentage = percentage
        return this
    }

    fun withDateFrom(aDate:LocalDate):DiscountByCategoryBuilder{
        this.dateFrom = aDate
        return this
    }

    fun withDateTo(aDate:LocalDate):DiscountByCategoryBuilder{
        this.dateTo = aDate
        return this
    }

    fun withCategoryName(categoryName:String):DiscountByCategoryBuilder{
        this.categoryName = categoryName
        return this
    }

    fun build(): DiscountByCategory {
        return DiscountByCategory(percentage, dateFrom, dateTo, categoryName)
    }
}

class DiscountByProductBuilder{
    var percentage: Double = 0.00
    var dateFrom: LocalDate = LocalDate.MIN
    var dateTo: LocalDate = LocalDate.MIN
    var aProduct: Product = ProductBuilder.aProduct().build()

    companion object {
        @JvmStatic
        fun aDiscount():DiscountByProductBuilder{
            return DiscountByProductBuilder()
        }
    }

    fun build(): DiscountByProduct {
        return DiscountByProduct(percentage, dateFrom, dateTo, aProduct)
    }

    fun withPercentage(percentage:Double):DiscountByProductBuilder{
        this.percentage = percentage
        return this
    }

    fun withDateFrom(aDate:LocalDate):DiscountByProductBuilder{
        this.dateFrom = aDate
        return this
    }

    fun withDateTo(aDate:LocalDate):DiscountByProductBuilder{
        this.dateTo = aDate
        return this
    }

    fun withProduct(aProduct: Product):DiscountByProductBuilder{
        this.aProduct = aProduct
        return this
    }
}

class DiscountByProductsBuilder{
    var percentage: Double = 0.00
    var dateFrom: LocalDate = LocalDate.MIN
    var dateTo: LocalDate = LocalDate.MIN
    var listOfProducts: MutableList<Product> = mutableListOf()

    companion object {
        @JvmStatic
        fun aDiscount():DiscountByProductsBuilder{
            return DiscountByProductsBuilder()
        }
    }

    fun build(): DiscountByProducts {
        return DiscountByProducts(percentage, dateFrom, dateTo, listOfProducts)
    }

    fun withPercentage(percentage:Double):DiscountByProductsBuilder{
        this.percentage = percentage
        return this
    }

    fun withDateFrom(aDate:LocalDate):DiscountByProductsBuilder{
        this.dateFrom = aDate
        return this
    }

    fun withDateTo(aDate:LocalDate):DiscountByProductsBuilder{
        this.dateTo = aDate
        return this
    }

    fun withProducts(listOfProducts: MutableList<Product>):DiscountByProductsBuilder{
        this.listOfProducts = listOfProducts
        return this
    }
}

class GeographicMapBuilder{
    var latitude:Double = 0.00
    var longitude:Double = 0.00

    companion object{
        @JvmStatic
        fun aGeographicMap():GeographicMapBuilder{
            return  GeographicMapBuilder()
        }
    }

    fun build(): GeographicMap {
        return GeographicMap(latitude, longitude)
    }

    fun withLatitude(aLatitud:Double):GeographicMapBuilder {
        this.latitude = aLatitud
        return this
    }

    fun withLongitude(aLongitude:Double):GeographicMapBuilder {
        this.longitude = aLongitude
        return this
    }
}

class AddressBuilder(){
    var locality: String = ""
    var street: String = ""
    var number:Long = 0
    var zone : GeographicMap = GeographicMapBuilder.aGeographicMap().build()

    companion object{
        @JvmStatic
        fun anAddress():AddressBuilder{
            return AddressBuilder()
        }
    }

    fun build(): Address {
        return Address(locality, street, number, zone)
    }

    fun withLocality(aLocality:String):AddressBuilder{
        this.locality = aLocality
        return this
    }

    fun withStreet(aStreet:String):AddressBuilder{
        this.street = aStreet
        return this
    }

    fun withNumber(aNumber:Long):AddressBuilder{
        this.number = aNumber
        return this
    }

    fun withZone(aGeographicZone: GeographicMap):AddressBuilder{
        this.zone = aGeographicZone
        return this
    }

}

class PickupInStoreBuilder(){
    var date: LocalDate = LocalDate.MIN
    var hour: LocalTime = LocalTime.MIDNIGHT
    var storeAddress: String  = ""

    companion object{
        @JvmStatic
        fun aPickupInStore():PickupInStoreBuilder{
            return PickupInStoreBuilder()
        }
    }

    fun build(): PickupInStore {
        return PickupInStore(date, hour, storeAddress)
    }

    fun withDate(aDate:LocalDate):PickupInStoreBuilder{
        this.date = aDate
        return this
    }

    fun withHour(anHour:LocalTime):PickupInStoreBuilder{
        this.hour = anHour
        return this
    }

    fun withStoreAddress(anAddress:String):PickupInStoreBuilder{
        this.storeAddress = anAddress
        return this
    }

}

class HomeDeliveryBuilder(){
    var date: LocalDate = LocalDate.MIN
    var hour: LocalTime = LocalTime.MIDNIGHT
    var address: String  = ""

    companion object{
        @JvmStatic
        fun aHomeDelivery():HomeDeliveryBuilder{
            return HomeDeliveryBuilder()
        }
    }

    fun build(): HomeDelivery {
        return HomeDelivery(date, hour, address)
    }

    fun withDate(aDate:LocalDate):HomeDeliveryBuilder{
        this.date = aDate
        return this
    }

    fun withHour(anHour:LocalTime):HomeDeliveryBuilder{
        this.hour = anHour
        return this
    }

    fun withAddress(anAddress:String):HomeDeliveryBuilder{
        this.address = anAddress
        return this
    }

}

class TurnBuilder{
    var day = LocalDate.MIN
    var hour = LocalTime.MIN
    companion object{
        @JvmStatic
        fun aTurn():TurnBuilder{
            return TurnBuilder()
        }
    }

    fun build(): Turn {
        return Turn(day, hour)
    }
}