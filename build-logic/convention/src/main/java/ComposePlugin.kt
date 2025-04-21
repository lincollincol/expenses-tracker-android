
import com.android.build.api.dsl.CommonExtension
import com.lincollincol.convention.plugins
import com.lincollincol.convention.alias
import com.lincollincol.convention.configureLibrary
import com.lincollincol.convention.configureBaseAndroid
import com.lincollincol.convention.debugImplementation
import com.lincollincol.convention.implementation
import com.lincollincol.convention.libs
import com.lincollincol.convention.namespaceSuffix
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class ComposePlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        plugins {
            alias(libs.plugins.kotlin.compose)
        }
        dependencies {
            implementation(libs.androidx.appcompat)
            implementation(libs.androidx.navigation.compose)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.material.asProvider())
            implementation(platform(libs.androidx.compose.bom))
            implementation(libs.androidx.ui.asProvider())
            implementation(libs.androidx.ui.graphics)
            implementation(libs.androidx.ui.tooling.preview)
            implementation(libs.androidx.material3)
            implementation(libs.material.motion.compose)
            debugImplementation(libs.androidx.ui.tooling.asProvider())
            debugImplementation(libs.androidx.ui.test.manifest)
        }
    }
}

