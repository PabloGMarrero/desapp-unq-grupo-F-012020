package unq.tpi.desapp

import java.time.LocalDate

abstract class Discount(percentage: Double, dateFrom: LocalDate, dateTo:LocalDate) {
    var percentage: Double = percentage
    var dateFrom: LocalDate = dateFrom
    var dateTo: LocalDate = dateTo
}