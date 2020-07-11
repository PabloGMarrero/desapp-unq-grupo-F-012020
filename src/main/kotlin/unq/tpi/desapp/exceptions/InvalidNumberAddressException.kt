package unq.tpi.desapp.exceptions

import java.lang.RuntimeException

/**
 * Exception to handle the invalid number address when an address is created
 */

class InvalidNumberAddressException(message:String) : RuntimeException(message)  {
}