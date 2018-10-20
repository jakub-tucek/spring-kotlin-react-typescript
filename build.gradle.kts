import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar
import org.springframework.boot.gradle.tasks.run.BootRun

allprojects {
    group = "com.example"
    version = "0.1-SNAPSHOT"
}

buildscript {
    val kotlinVersion = "1.2.51"
    val springBootVersion = "2.0.6.RELEASE"

    repositories {
        mavenCentral()
        gradlePluginPortal()
    }


    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:$springBootVersion")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("org.jetbrains.kotlin:kotlin-allopen:$kotlinVersion")
        classpath("com.moowork.gradle:gradle-node-plugin:1.2.0")
    }
}

subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-spring")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "com.moowork.node")


    repositories {
        mavenCentral()
    }

    dependencies {
        val implementation by configurations
        val testImplementation by configurations
        val compileOnly by configurations
        val runtimeOnly by configurations


        implementation("org.springframework.boot:spring-boot-starter-mustache")
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        runtimeOnly("org.springframework.boot:spring-boot-devtools")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.springframework.security:spring-security-test")
    }

    tasks {
        withType<KotlinCompile> {
            kotlinOptions {
                jvmTarget = "1.8"
                freeCompilerArgs = listOf("-Xjsr305=strict")
            }
        }
        // must be before BootJar because otherwise it will enable BootJar task for every sub project
        // will create jar files for submodules
        withType<Jar> {
            enabled = true
        }
        withType<BootJar> {
            enabled = false
        }
        withType<BootRun> {
            enabled = false
        }

        withType<JavaCompile> {
            sourceCompatibility = JavaVersion.VERSION_1_8.toString()
            targetCompatibility = JavaVersion.VERSION_1_8.toString()
        }
    }
}