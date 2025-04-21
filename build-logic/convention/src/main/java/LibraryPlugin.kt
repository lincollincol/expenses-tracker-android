
import com.lincollincol.convention.plugins
import com.lincollincol.convention.alias
import com.lincollincol.convention.configureLibrary
import com.lincollincol.convention.configureBaseAndroid
import com.lincollincol.convention.implementation
import com.lincollincol.convention.libs
import com.lincollincol.convention.namespaceSuffix
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class LibraryPlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        plugins {
            alias(libs.plugins.android.library)
            alias(libs.plugins.kotlin.android)
            alias(libs.plugins.expensestracker.hilt)
        }
        configureLibrary {
            configureBaseAndroid(this)
            namespace = "${Application.id}${target.namespaceSuffix}"
            defaultConfig {
                consumerProguardFiles("consumer-rules.pro")
            }
        }
        dependencies {
            implementation(libs.androidx.core.ktx)
        }
    }
}

