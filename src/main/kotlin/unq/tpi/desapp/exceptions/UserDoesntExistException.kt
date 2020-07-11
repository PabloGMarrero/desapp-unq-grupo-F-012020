package unq.tpi.desapp.exceptions

import java.lang.RuntimeException

/**
 * Exception to handle when a user does not exist
 */
class UserDoesntExistException(message:String) : RuntimeException(message)  {

}