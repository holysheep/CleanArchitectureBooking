object GradlePluginVersion {
    private const val GRADLE_VERSION = "2.3.0"
    const val GRADLE_ANDROID_TOOLS = "com.android.tools.build:gradle:${GRADLE_VERSION}"
    const val GRADLE_SAFE_ARGS_PLUGIN = "androidx.navigation:navigation-safe-args-gradle-plugin:${LibraryVersion.NAVIGATION}"
}

object GradlePackageId {
    const val ANDROID_APPLICATION = "com.android.application"
    const val ANDROID_LIBRARY = "com.android.library"
    const val KOTLIN_JVM = "org.jetbrains.kotlin.jvm"
    const val KOTLIN_ANDROID = "org.jetbrains.kotlin.android"
    const val KOTLIN_ANDROID_EXTENSIONS = "org.jetbrains.kotlin.android.extensions"
    const val KOTLIN_KAPT = "kotlin-kapt"
    const val SAFE_ARGS = "androidx.navigation.safeargs.kotlin"
}