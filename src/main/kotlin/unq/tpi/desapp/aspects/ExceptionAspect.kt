package unq.tpi.desapp.aspects

/**
 * Annotation class to use to help the handler error aspect.
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
annotation class ExceptionAspect