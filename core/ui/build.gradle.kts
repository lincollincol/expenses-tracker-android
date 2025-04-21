plugins {
    alias(libs.plugins.expensestracker.library)
    alias(libs.plugins.expensestracker.compose)
}

dependencies {

    implementation(projects.core.common)
    implementation(projects.core.model)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

}