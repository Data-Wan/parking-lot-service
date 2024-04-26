package assessment.parkinglot;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
class BasicArchitectureTest {
    private final JavaClasses importedClasses = new ClassFileImporter().importPackages("assessment.parkinglot");

    @Test
    void applicationLayerShouldNotDependOnPresentationLayer() {
        ArchRule rule = noClasses()
            .that().resideInAPackage("..application..")
            .should().dependOnClassesThat().resideInAPackage("..presentation..");

        rule.check(importedClasses);
    }

    @Test
    void infrastructureLayerShouldOnlyBeAccessedByApplicationLayer() {
        ArchRule rule = classes()
            .that().resideInAPackage("..infrastructure..")
            .should().onlyHaveDependentClassesThat().resideInAnyPackage("..application..", "..infrastructure..");

        rule.check(importedClasses);
    }
}
