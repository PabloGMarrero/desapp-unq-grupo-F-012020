package unq.tpi.desapp.exceptions

import java.lang.RuntimeException

class InvalidEmailOrPasswordException(message:String) : RuntimeException(message) {
}