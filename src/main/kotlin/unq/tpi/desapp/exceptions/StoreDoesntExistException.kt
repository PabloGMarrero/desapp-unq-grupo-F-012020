package unq.tpi.desapp.exceptions

import java.lang.RuntimeException

/**
 * Exception to handle when a store does not exist
 */
class StoreDoesntExistException(message:String) : RuntimeException(message) {
}