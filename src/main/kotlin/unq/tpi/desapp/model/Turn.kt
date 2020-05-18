package unq.tpi.desapp.model

import java.time.LocalDate
import java.time.LocalTime

/**
 * Turn represents the idea of what day and at what time a turn is taken by a purchase
 * @param day represents the day of the turn
 * @param hour represents the hour of the turn
 */
class Turn(day: LocalDate, hour: LocalTime) {
    var day = day
    var hour = hour
}