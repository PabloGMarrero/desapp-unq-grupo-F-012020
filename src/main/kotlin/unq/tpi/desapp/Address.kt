package unq.tpi.desapp

import unq.tpi.desapp.builders.GeographicMapBuilder
import java.lang.Math.pow
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.Entity
import kotlin.math.*
/**
 * Represent the address of an specific place
 * @param locality represents the locality where is the address
 * @param street represents the streef where is the address
 * @param number represents the number of the street where is the address
 * @param aZone represents the geographic zone in the map
 */

@Embeddable
class Address {
    private val EARTH_RADIUS:Double = 6371.0

    @Column
    var locality = ""
    @Column
    var street = ""
    @Column
    var number:Long = 0

    var geographicZone = GeographicMapBuilder.aGeographicMap().build()

    constructor()
    constructor(locality:String, street:String, number:Long, aZone: GeographicMap){
        this.locality = locality
        this.street = street
        this.number = number
        this.geographicZone = aZone
    }
    /**
     * Redifition of the equals method
     * @return true if the the Address has the same locality, street and number
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Address

        if (locality != other.locality) return false
        if (street != other.street) return false
        if (number != other.number) return false

        return true
    }

    /**
     *
     */
    override fun hashCode(): Int {
        var result = locality.hashCode()
        result = 31 * result + street.hashCode()
        result = 31 * result + number.hashCode()
        return result
    }


    /**
     * Calculate the distance between self and the address passed by param
     * @param anAddress the address to compare the distances
     * @return a double that representes the distance between self and address
     */
    fun calculateDistanceWithInKm(lat:Double, lon:Double):Double{
        var dLat:Double = deg2rad(this.differenceLatWith(lat))
        var dLon:Double = deg2rad(this.differenceLonWith(lon))
        var temp1:Double = pow(sin(dLat/2), 2.0)
        var temp2:Double = cosOfAddress(this.geographicZone.latitude) * cosOfAddress(lat) * pow(sin(dLon/2), 2.0)
        var sum:Double = temp1 + temp2
        var aTan:Double = 2 * atan2(sqrt(sum), sqrt(1 - sum))
        var res:Double = aTan * EARTH_RADIUS
        return floor(res * 100) / 100
    }

    /**
     * Calculates the difference of latitudes between self and another address
     * @return the difference expressed in a Double
     */
    private fun differenceLatWith(latitude:Double):Double = geographicZone.differenceLatWith(latitude)

    /**
     * Calculates the difference of longitudes between self and another address
     * @return the difference expressed in a Double
     */
    private fun differenceLonWith(longitude:Double):Double = geographicZone.differenceLonWith(longitude)

    /**
     * Calculates a degrade value to his radian value
     * @return the representation of the radianes
     */
    private fun deg2rad(aValue: Double): Double = aValue * (PI / 180)

    /**
     * Calculates the cosine of an address
     * @return the cosine expressed in radians
     */
    private fun cosOfAddress(latitude:Double):Double = cos(deg2rad(latitude))
}