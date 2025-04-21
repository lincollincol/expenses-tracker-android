package com.lincollincol.convention

import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.plugin.KaptExtension

/**
 * Common (base) config for android application and library modules
 */
internal fun Project.configureBaseAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    commonExtension.apply {
        compileSdk = Application.compileSdk
        defaultConfig {
            minSdk = Application.minSdk
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }
    }
    configureKotlinOptions()
//    configureKapt()
}

private fun Project.configureKotlinOptions() = configure<KotlinAndroidProjectExtension> {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_11)
    }
}

//private fun Project.configureKapt() = configure<KaptExtension> {
//    correctErrorTypes = true
//}