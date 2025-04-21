plugins {
    alias(libs.plugins.expensestracker.library)
    alias(libs.plugins.expensestracker.compose)
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

}