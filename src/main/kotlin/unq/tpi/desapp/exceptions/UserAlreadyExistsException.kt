package unq.tpi.desapp.exceptions

import java.lang.RuntimeException

/**
 * Exception to handle when a user is already exist and cant register another one with the same email
 */

class UserAlreadyExistsException(message:String) : RuntimeException(message){

}
