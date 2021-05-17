import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("android")
    kotlin("android.extensions")
    id("androidx.navigation.safeargs.kotlin")
    kotlin("kapt")
}

android {
    compileSdkVersion(Apps.compileSdk)

    defaultConfig {
        minSdkVersion(Apps.minSdk)
        targetSdkVersion(Apps.targetSdk)
        multiDexEnabled = true
        versionCode = Apps.versionCode
        versionName = Apps.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            buildConfigField("String", "API_BASE_URL", "\"https://dummyapi.io/data/api/\"")

            //fetch api key from local.properties file
            val key: String = gradleLocalProperties(rootDir).getProperty("API_KEY")
            buildConfigField("String", "API_KEY", key)
        }

        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }

    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(Libs.kotlin)
    implementation(Libs.coreKtx)
    implementation(Libs.appcompat)
    implementation(Libs.materialDesign)
    implementation(Libs.constraintLayout)
    implementation(Libs.legacySupport)
    implementation(Libs.navigationUi)
    implementation(Libs.navigationFragment)
    implementation(Libs.navigationFragmentKtx)
    implementation(Libs.navigationUiKtx)
    implementation(Libs.lifecycleExtension)

    //dagger dependencies
    implementation(Libs.dagger)
    implementation(Libs.daggerAndroid)
    implementation(Libs.daggerAndroidSupport)
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    kapt(Libs.daggerCompiler)
    kapt(Libs.daggerProcessor)

    //Room
    implementation(Libs.room)
    implementation(Libs.roomKtx)
    kapt(Libs.roomKapt)

    implementation(Libs.liveData)
    implementation(Libs.viewModel)

    implementation("de.hdodenhof:circleimageview:3.0.1")

    //picasso
    implementation(Libs.picasso)

    //Network Calls
    implementation(Libs.retrofit)
    implementation(Libs.okHttpLogging)
    implementation(Libs.retrofitGsonConverter)

    //Testing
    testImplementation(TestLibs.junit)
    testImplementation(TestLibs.mockito)
    testImplementation(TestLibs.mockitoInline)
    testImplementation(TestLibs.extJunit)
    testImplementation(TestLibs.espresso)
    testImplementation(TestLibs.coreTesting)
    testImplementation(TestLibs.testCoreLibrary)
    testImplementation(TestLibs.fragmentTesting)
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.3")
}

repositories {
    mavenCentral()
}