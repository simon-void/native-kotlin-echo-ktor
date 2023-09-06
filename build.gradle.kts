import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTargetWithHostTests

plugins {
    val kotlinVersion = "1.9.10"
    application
    kotlin("multiplatform") version kotlinVersion
}

repositories {
    mavenCentral()
}

kotlin {
    val hostOs: String = System.getProperty("os.name")
    val nativeTarget: KotlinNativeTargetWithHostTests = when {
        hostOs == "Linux" -> linuxX64("native")
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs.startsWith("Windows") -> mingwX64("native")
        // Other supported targets are listed here: https://kotlinlang.org/docs/multiplatform-dsl-reference.html#targets
        else -> throw GradleException("Host OS '$hostOs' is not configured (or possibly supported). current possible options are: 'Linux', 'Mac OS X', 'Windows*'")
    }

    nativeTarget.apply {
        binaries {
            executable {
                entryPoint = "main"
            }
        }
    }

    sourceSets {
        val ktorVersion = "2.3.4"

        val nativeMain by getting {
            dependencies {
                implementation("io.ktor:ktor-server-core:$ktorVersion")
                implementation("io.ktor:ktor-server-cio:$ktorVersion")
            }
        }
        val nativeTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}
