plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}
kotlin {
    jvmToolchain(17)
}
dependencies {
//    implementation(libs.javax.inject)
    implementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.junit)
}
