package unq.tpi.desapp.exceptions

import java.lang.RuntimeException

/**
 * Exception to handle the invalid brand product when the product is created
 */
class InvalidBrandProductException(message:String) : RuntimeException(message) {
}