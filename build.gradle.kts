buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath ("io.realm:realm-gradle-plugin:10.16.1")
    }
}
plugins {
    id("com.android.application") version "8.1.1" apply false
}