plugins {
    id("com.android.application")
}

android {
    namespace = "com.AlixZDev01.supermarket"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.AlixZDev01.supermarket"
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    //Lottie & YoYo
    implementation ("com.airbnb.android:lottie:6.3.0")
    implementation ("com.daimajia.androidanimations:library:2.4@aar")
    //Room
    implementation ("androidx.room:room-runtime:2.6.1")
    implementation("androidx.navigation:navigation-fragment:2.7.5")
    implementation("androidx.navigation:navigation-ui:2.7.5")
    annotationProcessor ("androidx.room:room-compiler:2.6.1")
    //Neshan sdk library
    implementation ("neshan-android-sdk:mobile-sdk:1.0.3")
    implementation ("neshan-android-sdk:services-sdk:1.0.0")
    implementation ("neshan-android-sdk:common-sdk:0.0.3")
    //Play Services
    implementation ("com.google.android.gms:play-services-gcm:17.0.0")
    implementation ("com.google.android.gms:play-services-location:21.0.1")
    //Retrofit & Gson & GsonConvertor
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.google.code.gson:gson:2.10.1")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    //DotIndicator
    implementation ("com.tbuonomo:dotsindicator:5.0")
    //CardView
    implementation ("androidx.cardview:cardview:1.0.0")
    //Glide & CircleImageView
    implementation ("com.github.bumptech.glide:glide:4.16.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}