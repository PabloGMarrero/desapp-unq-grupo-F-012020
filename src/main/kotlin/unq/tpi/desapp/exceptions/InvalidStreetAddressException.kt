package unq.tpi.desapp.exceptions

import java.lang.RuntimeException

/**
 * Exception to handle the invalid street address when an address is created
 */
class InvalidStreetAddressException(message:String) : RuntimeException(message)  {
}