plugins {
    kotlin("jvm") version "1.6.0"
    `maven-publish`
}

group = "com.andreikingsley"
val ggDSLVersion = "0.1.2-dev-1.9-fix-0.2"
version = ggDSLVersion

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
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
