package unq.tpi.desapp

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import unq.tpi.desapp.builders.PurchaseBuilder
import java.time.LocalDate


@SpringBootTest
class PurchaseTest {
    val aProduct = Product(1, "blabla","Pepitos",35.5, "Bagley")
    val aStore = Store("Kiosko","Emilio J 2020", 20.0)
    val aItem = Item(aProduct,1.0 ,aStore)
    val aUser = User("Pelufo","123","pelufo@pelufo")

    @Test
    fun testPurchaseAddItem(){
        var purchase = PurchaseBuilder.aPurchase().build()
        purchase.addItem(aItem)
        assert(purchase.itemList.contains(aItem))
    }

    @Test
    fun testPurchaseGetTotal(){
        var purchase = PurchaseBuilder.aPurchase().build()
        purchase.addItem(aItem)
        assertEquals(purchase.getTotal(), 35.5)

    }
}