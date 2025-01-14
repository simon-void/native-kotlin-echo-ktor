
plugins {
    val kotlinVersion = "2.1.0"
    kotlin("multiplatform") version kotlinVersion
}

repositories {
    mavenCentral()
}

kotlin {
    fun prop(name: String): String = requireNotNull(System.getProperty(name)) {"property $name not found"}
    val hostOs = prop("os.name")
    val arch = prop("os.arch")
    val nativeTarget = when {
        hostOs == "Mac OS X" && arch == "x86_64" -> macosX64("native")
        hostOs == "Mac OS X" && arch == "aarch64" -> macosArm64("native")
        hostOs == "Linux" -> linuxX64("native")
        // Other supported targets are listed here: https://ktor.io/docs/native-server.html#targets
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    nativeTarget.apply {
        binaries {
            executable {
                entryPoint = "main"
            }
        }
    }

    sourceSets {
        val ktorVersion = "3.0.3"

        nativeMain.dependencies {
            implementation("io.ktor:ktor-server-core:$ktorVersion")
            implementation("io.ktor:ktor-server-cio:$ktorVersion")
        }
        nativeTest.dependencies {
            implementation(kotlin("test"))
            implementation("io.ktor:ktor-server-test-host:$ktorVersion")
        }
    }
}
