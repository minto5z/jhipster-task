package com.mintosoft.task;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.mintosoft.task");

        noClasses()
            .that()
            .resideInAnyPackage("com.mintosoft.task.service..")
            .or()
            .resideInAnyPackage("com.mintosoft.task.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.mintosoft.task.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
