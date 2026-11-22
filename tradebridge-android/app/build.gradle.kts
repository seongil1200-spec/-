plugins { id("com.android.application") }

android {
    namespace = "com.tradebridge.us"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.tradebridge.us"
        minSdk = 26
        targetSdk = 36
        versionCode = 2
        versionName = "1.0.1-rc2"
    }

    buildTypes { release { isMinifyEnabled = false } }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies { implementation("androidx.browser:browser:1.8.0") }
