package unq.tpi.desapp.exceptions

import java.lang.RuntimeException

/**
 * Exception to handle the invalid address locality when the address is created
 */
class InvalidLocalityAddressException(message:String) : RuntimeException(message)  {
}