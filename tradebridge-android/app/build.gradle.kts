plugins { id("com.android.application") }

android {
    namespace = "com.tradebridge.us"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.tradebridge.us"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0-rc1"
    }

    buildTypes { release { isMinifyEnabled = false } }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies { implementation("androidx.browser:browser:1.8.0") }
