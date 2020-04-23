package unq.tpi.desapp

import java.time.LocalDate

/**
 * DiscountByProduct represents the discount appliest to a list of product.
 *
 * @param percentage represents the percentage of discount.
 * @param dateFrom represents from which date is valid the discount.
 * @param dateTo represents to which date is valid the discount.
 * @param listOfProducts represents the list of products applies the discount.
 */

class DiscountByProducts(percentage: Double, dateFrom: LocalDate, dateTo:LocalDate, listOfProducts: MutableList<Product> )
    :Discount(percentage, dateFrom, dateTo) {
    var listOfProducts: MutableList<Product> = listOfProducts
}