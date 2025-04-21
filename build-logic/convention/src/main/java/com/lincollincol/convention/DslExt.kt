package com.lincollincol.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.plugins.PluginManager
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.gradle.plugin.use.PluginDependency

internal val Project.libs: LibrariesForLibs get() = (this as ExtensionAware).extensions.getByType()

internal fun Project.plugins(block: PluginManager.() -> Unit) = block(pluginManager)

internal fun PluginManager.alias(notation: Provider<PluginDependency>) =
    apply(notation.get().pluginId)

internal fun Project.configureApplication(action: ApplicationExtension.() -> Unit) =
    extensions.configure<ApplicationExtension>(action)

internal fun Project.configureLibrary(action: LibraryExtension.() -> Unit) =
    extensions.configure<LibraryExtension>(action)

internal fun DependencyHandlerScope.ksp(
    dependency: Provider<MinimalExternalModuleDependency>
) = add("ksp", dependency)

internal fun DependencyHandlerScope.implementation(
    dependency: Provider<MinimalExternalModuleDependency>
) = add("implementation", dependency)

internal fun DependencyHandlerScope.debugImplementation(
    dependency: Provider<MinimalExternalModuleDependency>
) = add("debugImplementation", dependency)

internal fun DependencyHandlerScope.implementation(dependency: ProjectDependency) =
    add("implementation", dependency)

/**
 * Returns namespace 'suffix' based on project's path
 */
internal val Project.namespaceSuffix: String get() =
    path.replace(':', '.').replace('-', '_')
