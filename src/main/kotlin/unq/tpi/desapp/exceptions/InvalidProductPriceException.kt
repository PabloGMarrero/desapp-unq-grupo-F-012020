package unq.tpi.desapp.exceptions

import java.lang.RuntimeException

/**
 * Exception to handle the invalid product price when a a product is created
 */
class InvalidProductPriceException(message: String) : RuntimeException(message) {

}
