package unq.tpi.desapp.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import unq.tpi.desapp.builders.AddressBuilder
import unq.tpi.desapp.builders.PurchaseBuilder
import unq.tpi.desapp.builders.UserBuilder
import unq.tpi.desapp.dto.UserDto
import kotlin.test.assertNotEquals

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

    @Test
    fun twoBasicUsersAreEquals(){
        var userA = UserBuilder.anUser().build()
        var userB = UserBuilder.anUser().build()

        assertEquals(userA, userB)
    }

    @Test
    fun twoUsersWithDifferentsEmailsAreNotEquals(){
        var userA = UserBuilder.anUser().withEmail("pablo@gmail.com").build()
        var userB = UserBuilder.anUser().build()

        assertNotEquals(userA, userB)
    }

    @Test
    fun twoUsersWithDifferentsNameAreNotEquals(){
        var userA = UserBuilder.anUser().withName("Pablo").build()
        var userB = UserBuilder.anUser().build()

        assertNotEquals(userA.hashCode(), userB.hashCode())
        assertNotEquals(userA, userB)
    }

    @Test
    fun twoUsersWithDifferentsPasswordsAreEquals(){
        var userA = UserBuilder.anUser().withPass("abc123").build()
        var userB = UserBuilder.anUser().build()

        assertEquals(userA.hashCode(), userB.hashCode())
        assertEquals(userA, userB)
    }

    @Test
    fun testCreationUserDtoFromUser(){
        var userDto = UserBuilder.anUser().withEmail("pablo@gmail.com").withName("Pablo").withPass("abc123").build().toUserDTO()

        assertEquals(userDto.email, "pablo@gmail.com")
        assertEquals(userDto.name, "Pablo")
        assertEquals(userDto.password, "")
    }

    @Test
    fun testCreationUserFromUserDto(){
        var userDto = UserDto("Pablo", "pablo@gmail.com", "", 0).userDtoToUser()

        assertEquals(userDto.email, "pablo@gmail.com")
        assertEquals(userDto.name, "Pablo")
        assertEquals(userDto.password, "")
    }
}