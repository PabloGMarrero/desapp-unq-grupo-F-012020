package unq.tpi.desapp.model

import org.joda.time.DateTime

/**
 * Open hours represents the concept which day is open with specific hour
 */
class OpenHours(day:String, hourFrom:DateTime, hourTo:DateTime){
    var day: String = day
    var hourFrom: DateTime = hourFrom
    var hourTo: DateTime = hourTo
}