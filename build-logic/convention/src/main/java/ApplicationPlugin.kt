import com.lincollincol.convention.alias
import com.lincollincol.convention.configureApplication
import com.lincollincol.convention.configureBaseAndroid
import com.lincollincol.convention.libs
import com.lincollincol.convention.plugins
import org.gradle.api.Plugin
import org.gradle.api.Project

class ApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        plugins {
            alias(libs.plugins.android.application)
            alias(libs.plugins.kotlin.android)
            alias(libs.plugins.expensestracker.compose)
            alias(libs.plugins.expensestracker.hilt)
            alias(libs.plugins.kotlin.serialization)
        }
        configureApplication {
            configureBaseAndroid(this)
            namespace = Application.id
            defaultConfig {
                applicationId = Application.id
                targetSdk = Application.targetSdk
                versionCode = Application.versionCode
                versionName = Application.versionName
            }
            buildTypes {
                named("debug") {
                    isMinifyEnabled = false
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
            }
            buildFeatures {
                compose = true
            }
        }
    }
}

