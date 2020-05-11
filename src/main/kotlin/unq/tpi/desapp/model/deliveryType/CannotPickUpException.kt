package unq.tpi.desapp.model.deliveryType

import java.lang.Exception

/**
 * Exception when an  user tries to pickup the purchase and is not a valid operation
 */
class CannotPickUpException(message:String): Exception(message)  {
}