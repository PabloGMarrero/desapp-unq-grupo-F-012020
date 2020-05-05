package unq.tpi.desapp.model

import org.springframework.boot.test.context.SpringBootTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import unq.tpi.desapp.builders.DiscountByCategoryBuilder
import java.time.LocalDate


@SpringBootTest
class DiscountByCategoryTest {

    @Test
    fun testConstructorDefaultParameters() {
        var discount = DiscountByCategoryBuilder.aDiscount().build()
        assertEquals(discount.percentage, 0.00)
        assertEquals(discount.dateFrom, LocalDate.MIN)
        assertEquals(discount.dateTo, LocalDate.MIN)
        assertEquals(discount.categoryName, "")
    }

    @Test
    fun testDiscountWithPercentage10(){
        var discount = DiscountByCategoryBuilder.aDiscount().withPercentage(10.0).build()
        assertEquals(discount.percentage, 10.0)
    }

    @Test
    fun testDiscountWithDateFromAndDateToNow(){
        var discount = DiscountByCategoryBuilder.aDiscount().withDateFrom(LocalDate.now()).withDateTo(LocalDate.now()).build()
        assertEquals(discount.dateFrom, LocalDate.now())
        assertEquals(discount.dateTo, LocalDate.now())
    }

    @Test
    fun testDiscountWithCategoryName(){
        var discount = DiscountByCategoryBuilder.aDiscount().withCategoryName("Bebidas").build()
        assertEquals(discount.categoryName, "Bebidas")
    }


}