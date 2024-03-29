
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "ru.netology.linkedout"
    compileSdk = 33

    defaultConfig {
        applicationId = "ru.netology.linkedout"
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.navigation:navigation-fragment-ktx:2.6.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.6.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation ("com.nambimobile.widgets:expandable-fab:1.2.1")
    implementation("androidx.browser:browser:1.5.0")
    implementation("com.google.dagger:hilt-android:2.44")
    kapt ("com.google.dagger:hilt-compiler:2.44.2")


    implementation("androidx.hilt:hilt-navigation-fragment:1.0.0")
    implementation("me.saket:better-link-movement-method:2.2.0")
    implementation("com.github.bumptech.glide:glide:4.15.0")
    kapt ("com.github.bumptech.glide:compiler:4.15.0")
    implementation ("com.github.bumptech.glide:okhttp3-integration:4.11.0")
    implementation("androidx.activity:activity-ktx:1.6.1")
    implementation("androidx.fragment:fragment-ktx:1.5.5")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-coroutines-play-services:1.5.1")

    implementation("com.google.android.exoplayer:exoplayer:2.18.4")

    implementation("androidx.paging:paging-runtime-ktx:3.2.1")
    implementation("androidx.coordinatorlayout:coordinatorlayout:1.2.0")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    implementation("androidx.room:room-runtime:2.6.1")
    kapt ("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    implementation("androidx.room:room-paging:2.6.1")

    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.2")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")

    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))

    implementation ("com.github.dhaval2404:imagepicker:2.1")

    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")

}