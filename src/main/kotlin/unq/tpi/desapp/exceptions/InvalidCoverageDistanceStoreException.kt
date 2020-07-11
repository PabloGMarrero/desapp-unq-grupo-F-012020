package unq.tpi.desapp.exceptions

import java.lang.RuntimeException

/**
 * Exception to handle the invalid coverage distance when an address is created
 */
class InvalidCoverageDistanceStoreException(message:String) : RuntimeException(message)  {
}