plugins {
    application
}

application {
    mainClassName = "examples.MainKt"
}

dependencies {
    implementation(project(":script-dsl"))
}
