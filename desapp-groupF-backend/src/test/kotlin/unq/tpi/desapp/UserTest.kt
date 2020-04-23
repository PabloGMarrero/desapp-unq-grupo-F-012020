package unq.tpi.desapp

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import unq.tpi.desapp.builders.UserBuilder

@SpringBootTest
class UserTest {
    val aProduct = Product(1, "blabla", "Pepitos", 35.5, "Bagley")
    val aStore = Store("Kiosko", "Emilio J 2020", 20.0)
    val aItem = Item(aProduct, 1.0, aStore)
    val aUser = User("Pelufo", "123", "pelufo@pelufo")
    val aPurchase = Purchase(aUser, "Envio a Domicilio")
    val aCategory = User.Categories("Almacen")

    @Test
    fun TestUserAdddItemProduct() {
        var user = UserBuilder.aUser().build()
        user.addItemProduct(aItem)
        assert(user.shoppingBag.contains(aItem))
    }

    @Test
    fun TestUserAddToAlHistorial() {
        var user = UserBuilder.aUser().build()
        user.addToHistorial(aPurchase)
        assert(user.historialPurchases.contains(aPurchase))
    }

    @Test
    fun TestUserChangePurchaseRange() {
        var user = UserBuilder.aUser().build()
        user.changePurchaseRange(10.1)
        assertEquals(user.purchaseRange, 10.1)
    }

    @Test
    fun TestUserAddCategory() {
        var user = UserBuilder.aUser().build()
        user.addCategory(aCategory)
        assert(user.categoryPreferences.contains(aCategory))
    }
}