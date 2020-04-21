package unq.tpi.desapp

import java.time.LocalDate

class Discount(percentage: Double, dateFrom: LocalDate, dateTo:LocalDate, product: Product) {
    var percentage: Double = percentage
    var product: Product =  product
    var dateFrom: LocalDate = dateFrom
    var DateTo: LocalDate = dateTo


}