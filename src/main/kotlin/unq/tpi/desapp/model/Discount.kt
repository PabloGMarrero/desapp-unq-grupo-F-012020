package unq.tpi.desapp.model

import java.time.LocalDate

/**
 * Discount is the abstract class to represent the discount types.
 *
 * @param percentage represents the percentage of discount.
 * @param dateFrom represents from which date is valid the discount.
 * @param dateTo represents to which date is valid the discount.
 */
abstract class Discount(percentage: Double, dateFrom: LocalDate, dateTo:LocalDate) {
    var percentage: Double = percentage
    var dateFrom: LocalDate = dateFrom
    var dateTo: LocalDate = dateTo
}