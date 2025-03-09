plugins {
    id("java")
    id("maven-publish")
    id("java-gradle-plugin")
    id("com.diffplug.spotless") version "7.0.2"
}

group = "cc.schimmer"

gradlePlugin {
    plugins {
        create("schimmerGradle") {
            id = "cc.schimmer-gradle"
            implementationClass = "cc.schimmer.SchimmerGradle"
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(gradleTestKit())
    testImplementation(platform("org.junit:junit-bom:5.11.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.slf4j:slf4j-simple:2.0.17")

    implementation(gradleApi())
    implementation("org.slf4j:slf4j-api:2.0.17")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.fasterxml.jackson.core:jackson-core:2.18.3")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.18.3")
}

tasks.test {
    useJUnitPlatform()
}

spotless {
    java {
        importOrder()
        removeUnusedImports()
        googleJavaFormat()
    }
}

publishing {
    repositories {
        maven {
            url = uri("https://nexus.fiereu.de/repository/maven-releases/")
            credentials {
                username = System.getenv("MAVEN_USERNAME")
                password = System.getenv("MAVEN_PASSWORD")
            }
        }
    }
    publications {
        create<MavenPublication>("nexus") {
            artifactId = "schimmer-gradle"
            version = System.getenv("SCHIMMER_VERSION")

            from(components["java"])
        }
    }
}