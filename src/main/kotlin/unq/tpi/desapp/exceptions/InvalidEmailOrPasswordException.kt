package unq.tpi.desapp.exceptions

import java.lang.RuntimeException

/**
 * Exception to handle the invalid email or password when the user tries register or login
 */
class InvalidEmailOrPasswordException(message:String) : RuntimeException(message) {
}