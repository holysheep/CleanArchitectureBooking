object LibraryVersion {
    const val RETROFIT = "2.7.1"
    const val OK_HTTP = "4.3.1"

    const val PLAY_CORE = "1.6.4"
    const val GSM_SERVICE = "17.0.0"
    const val APP_COMPAT = "1.1.0"
    const val RECYCLER_VIEW = "1.1.0"
    const val COORDINATOR_LAYOUT = "1.1.0"
    const val MATERIAL = "1.2.0-alpha03"
    const val CONSTRAINT_LAYOUT = "1.1.3"
    const val NAVIGATION = "2.1.0"
    const val LIFECYCLE = "1.1.1"
    const val LIFECYCLE_VIEW_MODEL_KTX = "2.1.0"

    const val KODEIN = "6.5.1"
}

object LibraryDependency {
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Core.KOTLIN}"
    const val KOTLIN_REFLECT = "org.jetbrains.kotlin:kotlin-reflect:${Core.KOTLIN}"
    const val COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Core.COROUTINES_ANDROID}"

    // Network
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${LibraryVersion.RETROFIT}"
    const val RETROFIT_MOSHI_CONVERTER = "com.squareup.retrofit2:converter-moshi:${LibraryVersion.RETROFIT}"

    const val OK_HTTP = "com.squareup.okhttp3:okhttp:${LibraryVersion.OK_HTTP}"
    const val LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${LibraryVersion.OK_HTTP}"

    const val APP_COMPAT = "androidx.appcompat:appcompat:${LibraryVersion.APP_COMPAT}"
    const val SUPPORT_CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${LibraryVersion.CONSTRAINT_LAYOUT}"
    const val PLAY_CORE = "com.google.android.play:core:${LibraryVersion.PLAY_CORE}"
    const val PLAY_SERVICES = "com.google.android.gms:play-services-location:${LibraryVersion.GSM_SERVICE}"

    const val RECYCLER_VIEW = "androidx.recyclerview:recyclerview:${LibraryVersion.RECYCLER_VIEW}"
    const val COORDINATOR_LAYOUT = "androidx.coordinatorlayout:coordinatorlayout:${LibraryVersion.COORDINATOR_LAYOUT}"
    const val MATERIAL = "com.google.android.material:material:${LibraryVersion.MATERIAL}"
    const val LIFECYCLE_EXTENSIONS = "android.arch.lifecycle:extensions:${LibraryVersion.LIFECYCLE}"
    const val LIFECYCLE_VIEW_MODEL_KTX =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${LibraryVersion.LIFECYCLE_VIEW_MODEL_KTX}"
    const val NAVIGATION_FRAGMENT_KTX = "androidx.navigation:navigation-fragment-ktx:${Core.NAVIGATION}"
    const val NAVIGATION_UI_KTX = "androidx.navigation:navigation-ui-ktx:${Core.NAVIGATION}"

    // DI
    const val KODEIN = "org.kodein.di:kodein-di-generic-jvm:${LibraryVersion.KODEIN}"
    const val KODEIN_DEFAULT = "org.kodein.di:kodein-di-framework-android-core:${LibraryVersion.KODEIN}"
    const val KODEIN_SUPPORT_LIB = "org.kodein.di:kodein-di-framework-android-support:${LibraryVersion.KODEIN}"
    const val KODEIN_ANDROID_X = "org.kodein.di:kodein-di-framework-android-x:${LibraryVersion.KODEIN}"
}

private object TestLibraryVersion {
    const val JUNIT = "4.13"
    const val ESPRESSO_CORE = "3.0.2"
    const val MOCKITO = "3.2.4"
    const val MOCKITO_KOTLIN = "2.2.0"
}

object TestLibraryDependency {
    const val JUNIT = "junit:junit:${TestLibraryVersion.JUNIT}"
    const val MOCKITO_INLINE = "org.mockito:mockito-inline:${TestLibraryVersion.MOCKITO}"
    const val MOCKITO_ANDROID = "org.mockito:mockito-android:${TestLibraryVersion.MOCKITO}"
    const val MOCKITO_KOTLIN = "com.nhaarman.mockitokotlin2:mockito-kotlin:${TestLibraryVersion.MOCKITO_KOTLIN}"
}
