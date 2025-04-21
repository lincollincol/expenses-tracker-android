import com.lincollincol.convention.alias
import com.lincollincol.convention.configureLibrary
import com.lincollincol.convention.implementation
import com.lincollincol.convention.libs
import com.lincollincol.convention.plugins
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class FeaturePlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        plugins {
            alias(libs.plugins.expensestracker.library)
            alias(libs.plugins.expensestracker.compose)
            alias(libs.plugins.kotlin.serialization)
        }
        configureLibrary {
            buildFeatures {
                compose = true
            }
        }
        dependencies {
            implementation(project(":core:ui"))
            implementation(project(":core:model"))
        }
    }
}

