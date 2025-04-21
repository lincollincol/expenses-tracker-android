plugins {
    alias(libs.plugins.expensestracker.library)
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.network)
}