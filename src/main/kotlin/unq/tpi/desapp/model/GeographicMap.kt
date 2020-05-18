package unq.tpi.desapp.model

/**
 * GeographicMap represents the object to manipulate distances between two points
 * @param latitude
 * @param longitude
 */
class GeographicMap (latitude:Double, longitude:Double ){
    var latitude:Double= latitude
    var longitude:Double= longitude


    override fun equals(anObject:Any?):Boolean{
        if (this ===anObject) return true
        if (anObject?.javaClass != javaClass) return false
        anObject as GeographicMap
        if (this.latitude != anObject.latitude || this.longitude != anObject.longitude ) return false
        return true
    }
}