package unq.tpi.desapp.exceptions

import java.lang.Exception
import java.lang.RuntimeException

class UserDoesntExistException(message:String) : RuntimeException(message)  {

}