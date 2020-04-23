package unq.tpi.desapp

import java.time.LocalDate

class DiscountByCategory (percentage: Double, dateFrom: LocalDate, dateTo:LocalDate, categoryName: String)
    :Discount(percentage, dateFrom, dateTo) {
    var categoryName = categoryName
}