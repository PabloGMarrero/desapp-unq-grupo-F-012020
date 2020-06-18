package unq.tpi.desapp.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import unq.tpi.desapp.Item
import unq.tpi.desapp.Product
import unq.tpi.desapp.Store
import unq.tpi.desapp.User
import unq.tpi.desapp.builders.AddressBuilder
import unq.tpi.desapp.builders.PurchaseBuilder
import unq.tpi.desapp.builders.UserBuilder

@SpringBootTest
class UserTest {
    val aProduct = Product(1, "blabla", "Pepitos", 35.5, "Bagley")
    val address = AddressBuilder.anAddress().build()
    val aStore = Store(1, "Kiosko", address, 20.0, "")
    val aItem = Item(aProduct, 1.0, aStore)
    val aPurchase = PurchaseBuilder.aPurchase().build()
    val aCategory = User.Categories("Almacen")

    @Test
    fun TestUserAdddItemProduct() {
        var user = UserBuilder.anUser().build()
        user.addItemProduct(aItem)
        assert(user.shoppingBag.contains(aItem))
    }

    @Test
    fun TestUserAddToAlHistorial() {
        var user = UserBuilder.anUser().build()
        user.addToHistorial(aPurchase)
        assert(user.historialPurchases.contains(aPurchase))
    }

    @Test
    fun TestUserChangePurchaseRange() {
        var user = UserBuilder.anUser().build()
        user.changePurchaseRange(10.1)
        assertEquals(user.purchaseRange, 10.1)
    }

    @Test
    fun TestUserAddCategory() {
        var user = UserBuilder.anUser().build()
        user.addCategory(aCategory)
        assert(user.categoryPreferences.contains(aCategory))
    }
}