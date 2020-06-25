package unq.tpi.desapp.architecture

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.library.Architectures
import org.junit.jupiter.api.Test

class DependenciesTest {

    @Test
    fun dependenciesAreRespected(){
        var importecClasses: JavaClasses = ClassFileImporter().importPackages("unq.tpi.desapp")

        var rule: ArchRule = Architectures.layeredArchitecture()
                .layer("Controllers").definedBy("unq.tpi.desapp.controllers")
                .layer("Service").definedBy("unq.tpi.desapp.service")
                .layer("Repository").definedBy("unq.tpi.desapp.repository")
                .layer("Configuration").definedBy("unq.tpi.desapp.configuration")
                .whereLayer("Controllers").mayNotBeAccessedByAnyLayer()
                .whereLayer("Service").mayOnlyBeAccessedByLayers("Controllers")
                .whereLayer("Repository").mayOnlyBeAccessedByLayers("Service", "Configuration")

        rule.check(importecClasses)
    }
}