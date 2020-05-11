package unq.tpi.desapp.model

/**
 * Represent the address of an specific place
 * @param locality represents the locality where is the address
 * @param street represents the streef where is the address
 * @param number represents the number of the street where is the address
 * @param aZone represents the geographic zone in the map
 */
class Address(locality:String, street:String, number:Long, aZone: GeographicMap) {
    var locality = locality
    var street = street
    var number = number
    var geographicZone = aZone
}