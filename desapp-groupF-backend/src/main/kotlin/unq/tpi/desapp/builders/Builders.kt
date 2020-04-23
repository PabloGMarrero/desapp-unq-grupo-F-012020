package unq.tpi.desapp.builders

import org.apache.tomcat.jni.Local
import shapeless.ops.nat
import unq.tpi.desapp.*
import java.time.LocalDate

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
        return DiscountByCategory(percentage,dateFrom,dateTo, categoryName)
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
        return DiscountByProduct(percentage,dateFrom,dateTo, aProduct)
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
        return DiscountByProducts(percentage,dateFrom,dateTo, listOfProducts)
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
