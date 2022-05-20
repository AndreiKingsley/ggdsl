plugins {
    kotlin("jvm") version "1.6.0"
    //id("org.jetbrains.kotlin.jupyter.api") version "0.11.0-62"
    id("org.jetbrains.kotlin.jupyter.api") version "0.11.0-89-1"
    `maven-publish`
}

group = "com.andreikingsley"
val ggDSLVersion = "0.6.4"
version = ggDSLVersion

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.andreikingsley"
            artifactId = "ggdsl"
            version = ggDSLVersion

            from(components["java"])
        }
    }
}
