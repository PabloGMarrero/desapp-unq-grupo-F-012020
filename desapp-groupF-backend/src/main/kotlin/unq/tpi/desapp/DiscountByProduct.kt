package unq.tpi.desapp

import java.time.LocalDate

class DiscountByProduct(percentage: Double, dateFrom: LocalDate, dateTo:LocalDate, aProduct: Product)
    :Discount(percentage, dateFrom, dateTo) {
    var aProduct = aProduct
}