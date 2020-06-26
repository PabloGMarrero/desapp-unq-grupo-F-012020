package unq.tpi.desapp.aspects

/**
 * Annotation class to use to help the logger aspect.
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
annotation class LoggingAspect