package unq.tpi.desapp

import java.time.LocalDate

class DiscountByProducts(percentage: Double, dateFrom: LocalDate, dateTo:LocalDate, listOfProducts: MutableList<Product> )
    :Discount(percentage, dateFrom, dateTo) {
    var listOfProducts: MutableList<Product> = listOfProducts
}