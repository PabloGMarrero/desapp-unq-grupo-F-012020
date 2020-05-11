package unq.tpi.desapp.model

import org.joda.time.DateTime


class OpenHours(day:String, hourFrom:DateTime, hourTo:DateTime){
    var day: String = day
    var hourFrom: DateTime = hourFrom
    var hourTo: DateTime = hourTo
}