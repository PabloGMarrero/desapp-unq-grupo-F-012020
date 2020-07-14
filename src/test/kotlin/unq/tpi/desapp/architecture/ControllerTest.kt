package unq.tpi.desapp.architecture

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import unq.tpi.desapp.aspects.ExceptionAspect


@AnalyzeClasses(packages = ["unq.tpi.desapp.controllers"])
class ControllerTest {

    var importedClasses: JavaClasses? = null

    @BeforeEach
    fun setUp(){
        importedClasses = ClassFileImporter().importPackages("unq.tpi.desapp.")
    }
    @ArchTest
    var controllersShouldBeSuffixed: ArchRule? = classes()
            .that().resideInAPackage("..controllers..")
            .should().haveSimpleNameEndingWith("Controller")

    @Test
    fun controllerClassesShouldBeAnnotatedWithControllerOrRestControllerAnnotation() {
        val rule: ArchRule = classes()
                .that().haveSimpleNameEndingWith("Controller")
                .should().beAnnotatedWith(RequestMapping::class.java)
                .orShould().beAnnotatedWith(RestController::class.java)
        rule.check(importedClasses)
    }

    @Test
    fun controllersShouldReturnResponseEntity() {
        methods().that().areDeclaredInClassesThat().resideInAPackage("..controllers..")
                .and().haveRawReturnType(ResponseEntity::class.java)
//              .because("we work with rich HTTP responses")
    }

    @Test
    fun controllerClassesShouldNtDependEachOther(){
        var rule:ArchRule = noClasses().that().haveSimpleNameEndingWith("Controller").should()
                .dependOnClassesThat().haveSimpleNameEndingWith("Controller")
        rule.check(importedClasses)
    }

    @ArchTest
    var `controllerMethodsShouldBeAnnotatedWithRequestMapping` = methods()
            .that().arePublic()
            .and().areDeclaredInClassesThat().resideInAPackage("..controllers..")
            .and().areDeclaredInClassesThat().areAnnotatedWith(RestController::class.java)
            .and().areNotAnnotatedWith(Autowired::class.java)
            .and().areAnnotatedWith(RequestMapping::class.java)
                        .should().beAnnotatedWith(GetMapping::class.java)
                                .orShould().beAnnotatedWith(PostMapping::class.java)
                                        .orShould().beAnnotatedWith(PutMapping::class.java)
                                                .orShould().beAnnotatedWith(DeleteMapping::class.java)

    @ArchTest
    var `controllerPostAndPutMethodsShouldBeAnnotatedWithExceptionAspectAnnotation` = methods().
            that().arePublic().
            and().areDeclaredInClassesThat().resideInAPackage("..controllers..").
            and().areDeclaredInClassesThat().areAnnotatedWith(RestController::class.java).
            and().areAnnotatedWith(RequestMapping::class.java).
            and().areAnnotatedWith(PutMapping::class.java).or().areAnnotatedWith(PostMapping::class.java).
            should().beAnnotatedWith(ExceptionAspect::class.java)

}
