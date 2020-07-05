package unq.tpi.desapp.architecture

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition
import org.junit.jupiter.api.Test
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@AnalyzeClasses(packages = ["unq.tpi.desapp.service"])
class ServiceTest {

//    @ArchTest
    var service_should_be_suffixed: ArchRule? = ArchRuleDefinition.classes()
                .that().resideInAPackage("..service..")
                .should().haveSimpleNameEndingWith("Service")

    @Test
    fun serviceClassesShouldBeAnnotatedWithServiceAndTransactionalAnnotation() {
        var importecClasses: JavaClasses = ClassFileImporter().importPackages("unq.tpi.desapp")

        val rule: ArchRule = ArchRuleDefinition.classes()
                .that().haveSimpleNameEndingWith("Service")
                .should().beAnnotatedWith(Transactional::class.java)
                .andShould().beAnnotatedWith(Service::class.java)
        rule.check(importecClasses)
    }
}