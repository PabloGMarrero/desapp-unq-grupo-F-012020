package unq.tpi.desapp.aspects

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.joda.time.DateTime
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.*
import java.util.stream.Collectors


/**
 * Aspect to logger the webservice
 */
@Component
@Aspect
class WebServiceLoggerAspect {
    companion object {
        val log: Logger = LoggerFactory.getLogger(this.javaClass.name)
    }


    /**
     *
     */
    @After("@annotation(unq.tpi.desapp.aspects.LoggingAspect)")
    fun logAfterMethod(joinPoint: JoinPoint){
        var infoResult:String = getInfo(joinPoint).stream().collect(Collectors.joining(""))
        log.info(infoResult)
    }

    private fun getInfo(joinPoint: JoinPoint):List<String>{
        val info: MutableList<String> = ArrayList()
        info.add("### LOGGING AFTER METHOD START ###")
        info.add(joinPoint.target.javaClass.simpleName)
        info.add(" -> " + joinPoint.signature.name)
        info.add("### ARGS ####")
        info.add("( " + this.argsToString(joinPoint.args).toString() + " )")
        info.add("### LOGGING AFTER METHOD END ###")
        return info
    }

    private fun argsToString(args: Array<Any>): String? {
        var result: String? = ""
        var isStart = true
        for (argument in args) {
            if (isStart) {
                isStart = false
            } else {
                result += ", "
            }
            result += argument
        }
        return result
    }

    /**
     *
     */
    @Around("@annotation(unq.tpi.desapp.aspects.LoggingAspect)")
    fun logAroundMethod(joinPoint: ProceedingJoinPoint): Any?{
        log.info("### LOGGING AROUND METHOD START ###")
        var startTime:DateTime = DateTime.now()

        var obj = joinPoint.proceed()

        var endTime:DateTime = DateTime.now()

        log.info("Time taken start at {} end at {}", startTime, endTime)
        log.info("### LOGGING AROUND METHOD END ###")


        return obj
    }
}