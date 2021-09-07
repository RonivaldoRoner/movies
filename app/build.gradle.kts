plugins {
    id(Dependency.Plugins.application)
    kotlin(Dependency.Plugins.android)
    kotlin(Dependency.Plugins.kapt)
    id("kotlin-android")
}

android {
    compileSdkVersion(Config.compileSdkVersion)
    defaultConfig {
        applicationId = Config.applicationId
        minSdkVersion(Config.minSdkVersion)
        targetSdkVersion(Config.targetSdkVersion)
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "baseUrl", "\"https://www.omdbapi.com/\"")
        buildConfigField("String", "apiKey", "\"82a421b4\"")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }

    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {

    implementation(Dependency.kotlin)
    implementation(Dependency.coreKtx)
    implementation(Dependency.appCompat)
    implementation(Dependency.lifecycleViewModel)
    implementation(Dependency.lifecycleLiveData)
    implementation(Dependency.coroutinesAndroid)
    implementation(Dependency.coroutinesCore)
    implementation(Dependency.material)
    implementation(Dependency.constraintLayout)
    implementation(Dependency.navigationFragment)
    implementation(Dependency.navigationUi)

    // Inject Dependencies
    implementation(Dependency.koinCore)
    implementation(Dependency.koinAndroid)
    implementation(Dependency.koinViewModel)
    implementation(Dependency.koinAndroidX)

    // Http Request
    implementation(Dependency.okhttp)
    implementation(Dependency.loggingInteceptor)
    implementation(Dependency.retrofit)
    implementation(Dependency.gson)
    implementation(Dependency.legacySupport)

    //Glide
    implementation(Dependency.glide)
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    annotationProcessor(Dependency.glideCompile)

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    compileOnly("org.jetbrains.kotlin:kotlin-stdlib")
    compileOnly("org.jetbrains.kotlin:kotlin-reflect")
    compileOnly("org.jetbrains.kotlin:kotlin-script-runtime")
    compileOnly("com.pinterest.ktlint:ktlint-core:0.34.2")
}