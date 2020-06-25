package unq.tpi.desapp.model

import javax.persistence.Embeddable

/**
 * GeographicMap represents the object to manipulate distances between two points
 * @param latitude
 * @param longitude
 */
@Embeddable
class GeographicMap {
    var latitude:Double= 0.0
    var longitude:Double= 0.0

    constructor()
    constructor(latitude:Double, longitude:Double ){
        this.longitude = longitude
        this.latitude = latitude
    }

    override fun equals(anObject:Any?):Boolean{
        if (this ===anObject) return true
        if (anObject?.javaClass != javaClass) return false
        anObject as GeographicMap
        if (this.latitude != anObject.latitude || this.longitude != anObject.longitude ) return false
        return true
    }

    /**
     * Calculates the difference between the self latitude with another one
     * @param latitude a double that represents the latitude to compare
     * @return a double that represents the difference
     */
    fun differenceLatWith(latitude: Double):Double = latitude - this.latitude

    /**
     * Calculates the difference between the self longitude with another one
     * @param longitude a double that represents the longitud to compare
     * @return a double that represents the difference
     */
    fun differenceLonWith(longitude: Double):Double = longitude - this.longitude

}