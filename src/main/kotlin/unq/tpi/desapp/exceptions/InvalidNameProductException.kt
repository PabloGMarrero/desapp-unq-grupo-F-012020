package unq.tpi.desapp.exceptions

import java.lang.RuntimeException

/**
 * Exception to handle the invalid name product when a product is created
 */

class InvalidNameProductException(message: String) : RuntimeException(message) {

}
