buildscript {
    repositories {
        google()
        jcenter()
//        maven { url 'https://maven.fabric.io/public' }
    }
    dependencies {
        classpath(GradlePluginVersion.GRADLE_ANDROID_TOOLS)
        classpath(kotlin("gradle-plugin", version = Core.KOTLIN))
        classpath(GradlePluginVersion.GRADLE_SAFE_ARGS_PLUGIN)
//        classpath 'io.fabric.tools:gradle:1.+'
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}