package unq.tpi.desapp.model

import org.springframework.boot.test.context.SpringBootTest
import unq.tpi.desapp.builders.ItemBuilder
import unq.tpi.desapp.builders.ProductBuilder
import unq.tpi.desapp.builders.StoreBuilder
import kotlin.test.Test
import kotlin.test.assertEquals

@SpringBootTest
class ItemTest {

    @Test
    fun testCreationItem(){
        var anItem = ItemBuilder.anItem().build()
        var aProduct = ProductBuilder.aProduct().build()
        var aStore = StoreBuilder.aStore().build()

        assertEquals(anItem.product, aProduct)
        assertEquals(anItem.quantity, 0.00)
        assertEquals(anItem.store, aStore)
    }

    @Test
    fun testItemWithTheSameProductTwice(){
        var aProduct = ProductBuilder.aProduct().build()
        var anItem = ItemBuilder.anItem().withProduct(aProduct).withQuantity(2.0)

        assertEquals(anItem.product, aProduct)
        assertEquals(anItem.quantity, 2.0)
    }
}