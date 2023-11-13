plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.foodexplorer"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.foodexplorer"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("androidx.room:room-runtime:2.2.0")
    annotationProcessor("androidx.room:room-compiler:2.2.0") // Fix the typo here

// add below dependency for using lifecycle extensions for room.

    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")

    annotationProcessor ("androidx.lifecycle:lifecycle-compiler:2.2.0")
}
