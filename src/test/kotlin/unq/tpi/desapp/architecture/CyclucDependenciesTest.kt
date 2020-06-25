package unq.tpi.desapp.architecture

import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices


@AnalyzeClasses(packages = ["unq.desapp.tpi"])
class CyclucDependenciesTest {

    @ArchTest
    val rule: ArchRule? = slices().matching("")
            .should().notDependOnEachOther()
}