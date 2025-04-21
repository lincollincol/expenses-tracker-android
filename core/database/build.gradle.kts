plugins {
    alias(libs.plugins.expensestracker.library)
    alias(libs.plugins.ksp)
}

dependencies {
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
}