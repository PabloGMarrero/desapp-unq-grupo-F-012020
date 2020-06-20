package unq.tpi.desapp.model

import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import unq.tpi.desapp.builders.DiscountByProductBuilder
import java.time.LocalDate

@SpringBootTest
class DiscountByProductTest {

    var aProduct = mockk<Product>()

    @Test
    fun testConstructorDefaultParameters() {
        var discount = DiscountByProductBuilder.aDiscount().build()
        assertEquals(discount.percentage, 0.00)
        assertEquals(discount.dateFrom, LocalDate.MIN)
        assertEquals(discount.dateTo, LocalDate.MIN)
    }

    @Test
    fun testDiscountWithPercentage10(){
        var discount = DiscountByProductBuilder.aDiscount().withPercentage(10.0).build()
        assertEquals(discount.percentage, 10.0)
    }

    @Test
    fun testDiscountWithDateFromAndDateToNow(){
        var discount = DiscountByProductBuilder.aDiscount().withDateFrom(LocalDate.now()).withDateTo(LocalDate.now()).build()
        assertEquals(discount.dateFrom, LocalDate.now())
        assertEquals(discount.dateTo, LocalDate.now())
    }

    @Test
    fun testDiscountWithProduct(){
        var discount = DiscountByProductBuilder.aDiscount().withProduct(aProduct)

        assertEquals(discount.aProduct, aProduct)
    }
}