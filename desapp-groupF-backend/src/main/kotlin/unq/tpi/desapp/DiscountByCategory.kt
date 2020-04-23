package unq.tpi.desapp

import java.time.LocalDate

/**
 * DiscountByCategory represents the discount only by one category.
 *
 * @param percentage represents the percentage of discount.
 * @param dateFrom represents from which date is valid the discount.
 * @param dateTo represents to which date is valid the discount.
 * @param categoryName represents the name of the category
 */
class DiscountByCategory (percentage: Double, dateFrom: LocalDate, dateTo:LocalDate, categoryName: String)
    :Discount(percentage, dateFrom, dateTo) {
    var categoryName = categoryName
}