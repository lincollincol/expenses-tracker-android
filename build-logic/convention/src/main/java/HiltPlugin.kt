
import com.lincollincol.convention.plugins
import com.lincollincol.convention.alias
import com.lincollincol.convention.configureLibrary
import com.lincollincol.convention.configureBaseAndroid
import com.lincollincol.convention.implementation
import com.lincollincol.convention.ksp
import com.lincollincol.convention.libs
import com.lincollincol.convention.namespaceSuffix
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltPlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        plugins {
            alias(libs.plugins.hilt)
            alias(libs.plugins.ksp)
        }
        dependencies {
            implementation(libs.hilt.android)
            ksp(libs.hilt.compiler)
        }
    }
}

