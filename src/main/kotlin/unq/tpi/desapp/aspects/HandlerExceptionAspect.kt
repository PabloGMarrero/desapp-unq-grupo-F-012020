package unq.tpi.desapp.aspects

import org.apache.http.HttpStatus
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException
import unq.tpi.desapp.exceptions.UserDoesntExistException
import org.springframework.http.HttpStatus.*
import unq.tpi.desapp.exceptions.InvalidEmailOrPasswordException
import unq.tpi.desapp.exceptions.StoreDoesntExistException
import unq.tpi.desapp.exceptions.UserAlreadyExistsException

@Aspect
@Component
class HandlerExceptionAspect {

    @Around("@annotation(unq.tpi.desapp.aspects.ExceptionAspect)")
    @Throws(Throwable::class)
    fun aroundHandlerError(joinPoint: ProceedingJoinPoint): Any? {
        return try {
            joinPoint.proceed()
        } catch (ex:UserDoesntExistException){
            throw ResponseStatusException(NOT_FOUND, ex.message, ex)
        }
        catch (ex:StoreDoesntExistException){
            throw ResponseStatusException(BAD_REQUEST, ex.message, ex)
        }
        catch(ex: UserAlreadyExistsException){
            throw ResponseStatusException(CONFLICT, ex.message, ex)
        }
        catch (ex: InvalidEmailOrPasswordException){
            throw ResponseStatusException(BAD_REQUEST, ex.message, ex)
        }
        catch (ex: RuntimeException) {
            throw ResponseStatusException(EXPECTATION_FAILED, ex.message)
        }
    }
}