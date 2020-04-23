package unq.tpi.desapp

import org.springframework.boot.test.context.SpringBootTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import unq.tpi.desapp.builders.ProductBuilder

@SpringBootTest
class ProductoTest {


    @Test
    fun testConstructorParametrosDefault() {
        var producto = ProductBuilder.aProduct().build()
        assertEquals(producto.id, 0)
        assertEquals(producto.imageUrl, "")
        assertEquals(producto.brand, "")
        assertEquals(producto.productName, "")
        assertEquals(producto.price, 0.0)
    }

    @Test
    fun testProductWithId100(){
        var producto = ProductBuilder.aProduct().withId(100).build()
        assertEquals(producto.id, 100)
    }

    @Test
    fun testProductWithUrlImage(){
        var producto = ProductBuilder.aProduct().withUrl("blabla").build()
        assertEquals(producto.imageUrl, "blabla")
    }

    @Test
    fun testProductWithBrand(){
        var producto = ProductBuilder.aProduct().withBrand("Marolio").build()
        assertEquals(producto.brand, "Marolio")
    }

    @Test
    fun testProductWithName(){
        var producto = ProductBuilder.aProduct().withName("Aceite").build()
        assertEquals(producto.productName, "Aceite")
    }

    @Test
    fun testProductWithPrice(){
        var producto = ProductBuilder.aProduct().withPrice(85.5).build()
        assertEquals(producto.price, 85.5)
    }

    @Test
    fun testProductWithPrice50AndBrandPepitos(){
        var producto = ProductBuilder.aProduct().withPrice(50.0).withBrand("Pepitos").build()
        assertEquals(producto.price, 50.0)
        assertEquals(producto.brand, "Pepitos")
    }

}