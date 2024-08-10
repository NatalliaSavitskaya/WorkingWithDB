plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.postgresql:postgresql:42.7.2") // add JDBC driver
    //implementation("javax.sql:javax.sql-api:4.1") // add PostgreSQL driver
}

tasks.test {
    useJUnitPlatform()
}