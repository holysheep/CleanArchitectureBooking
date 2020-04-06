import com.android.build.gradle.internal.dsl.BaseFlavor

plugins {
    id(GradlePackageId.ANDROID_APPLICATION)
    id(GradlePackageId.KOTLIN_ANDROID)
    id(GradlePackageId.KOTLIN_ANDROID_EXTENSIONS)
    id(GradlePackageId.KOTLIN_KAPT)
    id(GradlePackageId.SAFE_ARGS)
}

android {
    compileSdkVersion(AndroidConfig.COMPILE_SDK_VERSION)

    defaultConfig {
        applicationId = AndroidConfig.ID
        minSdkVersion(AndroidConfig.MIN_SDK_VERSION)
        targetSdkVersion(AndroidConfig.TARGET_SDK_VERSION)
        buildToolsVersion(AndroidConfig.BUILD_TOOLS_VERSION)

        versionCode = AndroidConfig.VERSION_CODE
        versionName = AndroidConfig.VERSION_NAME
        testInstrumentationRunner = AndroidConfig.TEST_INSTRUMENTATION_RUNNER

        buildConfigFieldFromGradleProperty("apiBaseUrl")
    }

    buildTypes {
        getByName(BuildType.RELEASE) {
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            proguardFiles("proguard-android.txt", "proguard-rules.pro")
        }

        getByName(BuildType.DEBUG) {
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
        }

        testOptions {
            unitTests.isReturnDefaultValues = TestOptions.IS_RETURN_DEFAULT_VALUES
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
    }

    lintOptions {
        isIgnoreTestSources = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    api(LibraryDependency.NAVIGATION_FRAGMENT_KTX)
    api(LibraryDependency.NAVIGATION_UI_KTX)

    implementation(LibraryDependency.OK_HTTP)
    implementation(LibraryDependency.PLAY_CORE)
    implementation(LibraryDependency.PLAY_SERVICES)
    api(LibraryDependency.RETROFIT)
    api(LibraryDependency.RETROFIT_MOSHI_CONVERTER)

    api(LibraryDependency.COROUTINES_ANDROID)
    api(LibraryDependency.APP_COMPAT)
    api(LibraryDependency.SUPPORT_CONSTRAINT_LAYOUT)
    api(LibraryDependency.COORDINATOR_LAYOUT)
    api(LibraryDependency.RECYCLER_VIEW)
    api(LibraryDependency.MATERIAL)
    api(LibraryDependency.LIFECYCLE_EXTENSIONS)
    api(LibraryDependency.LIFECYCLE_VIEW_MODEL_KTX)

    // DI
    api(LibraryDependency.KODEIN)
    api(LibraryDependency.KODEIN_ANDROID_X)
    api(LibraryDependency.KODEIN_SUPPORT_LIB)
    api(LibraryDependency.KODEIN_DEFAULT)

//    testImplementation(project(ModuleDependency.LIBRARY_TEST_UTILS))
//
//    testImplementation(TestLibraryDependency.JUNIT)
//    androidTestImplementation(TestLibraryDependency.TEST_RUNNER)
//    androidTestImplementation(TestLibraryDependency.ESPRESSO_CORE)
//    testImplementation(TestLibraryDependency.KLUENT)
//    androidTestImplementation(TestLibraryDependency.KLUENT_ANDROID)
//    testImplementation(TestLibraryDependency.MOCKITO_INLINE)
//    androidTestImplementation(TestLibraryDependency.MOCKITO_ANDROID)
//    testImplementation(TestLibraryDependency.MOCKITO_KOTLIN)
//    testImplementation(TestLibraryDependency.COROUTINES_TEST)
//    testImplementation(TestLibraryDependency.ANDROID_X_CORE_TESTING)
}

fun BaseFlavor.buildConfigFieldFromGradleProperty(gradlePropertyName: String) {
    val propertyValue = project.properties[gradlePropertyName] as? String
    checkNotNull(propertyValue) { "Gradle property $gradlePropertyName is null" }

    val androidResourceName = "AMAZON_${gradlePropertyName.toSnakeCase()}".toUpperCase()
    buildConfigField("String", androidResourceName, propertyValue)
}

fun String.toSnakeCase() = this.split(Regex("(?=[A-Z])")).joinToString("_") { it.toLowerCase() }


