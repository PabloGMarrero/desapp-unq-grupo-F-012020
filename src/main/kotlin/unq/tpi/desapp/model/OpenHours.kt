package unq.tpi.desapp.model

import org.joda.time.DateTime

/**
 * Open hours represents the concept which day is open with specific hour
 * @param day represent the day when the store is open
 * @param hourFrom represents from what hour is the store open
 * @param hourTo represents to what hour is the store open
 */
class OpenHours(day:String, hourFrom:DateTime, hourTo:DateTime){
    var day: String = day
    var hourFrom: DateTime = hourFrom
    var hourTo: DateTime = hourTo
}