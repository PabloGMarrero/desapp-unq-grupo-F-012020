package unq.tpi.desapp.model

import java.time.LocalDate

/**
 * DiscountByProduct represents the discount only by one product.
 *
 * @param percentage represents the percentage of discount.
 * @param dateFrom represents from which date is valid the discount.
 * @param dateTo represents to which date is valid the discount.
 * @param aProduct represents the product it applies the discount.
 */
class DiscountByProduct(percentage: Double, dateFrom: LocalDate, dateTo:LocalDate, aProduct: Product)
    : Discount(percentage, dateFrom, dateTo) {
    var aProduct = aProduct
}