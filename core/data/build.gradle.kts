plugins {
    alias(libs.plugins.expensestracker.library)
}

dependencies {

    implementation(projects.core.common)
    implementation(projects.core.model)
    implementation(projects.core.network)
    implementation(projects.core.database)
    implementation(libs.androidx.paging.runtime)

}