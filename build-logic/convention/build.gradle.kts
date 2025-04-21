import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_11
    }
}

dependencies {
    // Plugin libs is required for *Extension configuration classes
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    // Workaround for access to version catalog libs in the convention plugins
    compileOnly(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

gradlePlugin {
    plugins {
        register("androidCompose") {
            id = "expensestracker.compose"
            implementationClass = "ComposePlugin"
        }
        register("androidHilt") {
            id = "expensestracker.hilt"
            implementationClass = "HiltPlugin"
        }
        register("androidApplication") {
            id = "expensestracker.application"
            implementationClass = "ApplicationPlugin"
        }
        register("androidLibrary") {
            id = "expensestracker.library"
            implementationClass = "LibraryPlugin"
        }
        register("androidFeature") {
            id = "expensestracker.feature"
            implementationClass = "FeaturePlugin"
        }
    }
}
