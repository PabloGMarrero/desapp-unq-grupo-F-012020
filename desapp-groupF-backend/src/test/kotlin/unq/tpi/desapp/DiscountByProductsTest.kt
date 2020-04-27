package unq.tpi.desapp

import io.mockk.mockk
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import unq.tpi.desapp.builders.DiscountByProductsBuilder
import java.time.LocalDate

@SpringBootTest
class DiscountByProductsTest {


    var aProductA = mockk<Product>()
    var aProductB = mockk<Product>()
    var listOfProducts:  MutableList<Product> =  mutableListOf()

    @AfterEach
    fun SetUp(){
        listOfProducts.add(aProductA)
        listOfProducts.add(aProductB)
    }


    @Test
    fun testConstructorDefaultParameters() {
        var discount = DiscountByProductsBuilder.aDiscount().build()
        assertEquals(discount.percentage, 0.00)
        assertEquals(discount.dateFrom, LocalDate.MIN)
        assertEquals(discount.dateTo, LocalDate.MIN)
    }

    @Test
    fun testDiscountWithPercentage10(){
        var discount = DiscountByProductsBuilder.aDiscount().withPercentage(10.0).build()
        assertEquals(discount.percentage, 10.0)
    }

    @Test
    fun testDiscountWithDateFromAndDateToNow(){
        var discount = DiscountByProductsBuilder.aDiscount().withDateFrom(LocalDate.now()).withDateTo(LocalDate.now()).build()
        assertEquals(discount.dateFrom, LocalDate.now())
        assertEquals(discount.dateTo, LocalDate.now())
    }

    @Test
    fun testDiscountWithProduct(){

        var discount = DiscountByProductsBuilder.aDiscount().withProducts(listOfProducts)

        assertEquals(discount.listOfProducts, listOfProducts)
    }
}