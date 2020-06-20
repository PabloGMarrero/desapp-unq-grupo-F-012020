package unq.tpi.desapp.architecture

import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import org.springframework.stereotype.Repository

@AnalyzeClasses(packages = ["unq.tpi.desapp.repository"])
class RepositoryTest {

    @ArchTest
    var controllers_should_be_suffixed: ArchRule? = ArchRuleDefinition.classes()
            .that().resideInAPackage("..repository..")
            .should().haveSimpleNameEndingWith("Repository")

    @ArchTest
    var repositories_should_located_in_repository_package: ArchRule = classes()
            .that().areAnnotatedWith(Repository::class.java)
            .should().resideInAPackage("..repository..")
}