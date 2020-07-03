package unq.tpi.desapp.exceptions

import java.lang.RuntimeException

class StoreDoesntExistException(message:String) : RuntimeException(message) {
}