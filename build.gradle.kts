plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    implementation("org.springframework:spring-core:6.1.3")
    implementation("org.springframework:spring-context:6.1.3")
    implementation("org.springframework:spring-orm:6.0.0")
    implementation("org.springframework:spring-tx:6.0.0")

    implementation("org.hibernate.orm:hibernate-core:6.5.2.Final")
    implementation("org.hibernate.orm:hibernate-hikaricp:6.5.2.Final")

    implementation("org.postgresql:postgresql:42.7.2")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("ch.qos.logback:logback-classic:1.4.12")
}

tasks.test {
    useJUnitPlatform()
}