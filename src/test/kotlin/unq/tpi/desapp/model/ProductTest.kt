package unq.tpi.desapp.model

import org.springframework.boot.test.context.SpringBootTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import unq.tpi.desapp.builders.ProductBuilder
import unq.tpi.desapp.exceptions.InvalidBrandProductException
import unq.tpi.desapp.exceptions.InvalidNameProductException
import unq.tpi.desapp.exceptions.InvalidProductPriceException
import kotlin.test.assertFailsWith

@SpringBootTest
class ProductTest {


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

    @Test
    fun testOneProductIsEqualToAnotherOneWithSameAttributes(){
        var productA = ProductBuilder.aProduct().build()
        var productB = ProductBuilder.aProduct().build()

        assertEquals(productA, productB)
    }

    @Test
    fun testOneProductIsEqualToAnotherOneWithDifferentsAttributes(){
        var productA = ProductBuilder.aProduct().build()
        var productB = ProductBuilder.aProduct().withBrand("Coca").build()

        assertNotEquals(productA, productB)
    }

    @Test
    fun testEqualsAndHashCodeTest(){
        var product = ProductBuilder.aProduct().build()

        assertEquals(product, product)
        assertEquals(product.hashCode(), product.hashCode())
    }

    @Test
    fun testThrowInvalidProductNameExcepion(){
        assertFailsWith(InvalidNameProductException::class){
            var aProduct = ProductBuilder.aProduct().withBrand("Coke").withPrice(0.0).withName("").build()
            aProduct.validated()
        }
    }

    @Test
    fun testThrowInvalidBrandProductExcepion(){
        assertFailsWith(InvalidBrandProductException::class){
            var aProduct = ProductBuilder.aProduct().withBrand("")
                    .withPrice(0.0).withName("Coca-Cola").build()
            aProduct.validated()
        }
    }

    @Test
    fun testThrowInvalidProductPriceExcepion(){
        assertFailsWith(InvalidProductPriceException::class){
            var aProduct = ProductBuilder.aProduct().withBrand("Coke")
                    .withPrice(0.0).withName("Coca-Cola").build()
            aProduct.validated()
        }
    }

    @Test
    fun testThrowInvalidProductPriceExcepionNegativeNumbers(){
        assertFailsWith(InvalidProductPriceException::class){
            var aProduct = ProductBuilder.aProduct().withBrand("Coke")
                    .withPrice(-10.0).withName("Coca-Cola").build()
            aProduct.validated()
        }
    }

    @Test
    fun testDoesNotThrowAnyException(){
        var aProduct = ProductBuilder.aProduct().withBrand("Coke")
                    .withPrice(10.0).withName("Coca-Cola").build()
        aProduct.validated()
    }
}