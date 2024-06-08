import java.util.Date
import java.text.SimpleDateFormat

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.kapt)
}

android {
    namespace = "com.mvvm.jetpack"
    compileSdk = 34

    defaultConfig {
        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas"
            }
        }

        applicationId = "com.mvvm.jetpack"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs{
        getByName("debug") {
            enableV1Signing =true
            enableV2Signing =true
            enableV3Signing =true
            enableV4Signing =true
            storeFile = file("android.jks")
            storePassword = "123456"
            keyAlias = "key0"
            keyPassword = "123456"
        }
        register("release") {
            enableV1Signing =true
            enableV2Signing =true
            enableV3Signing =true
            enableV4Signing =true
            storeFile = file("android.jks")
            storePassword = "123456"
            keyAlias = "key0"
            keyPassword = "123456"
        }
    }

    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            signingConfig = signingConfigs.getByName("debug")
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    // 输出类型
    android.applicationVariants.all {
        // 编译类型
        val buildType = this.buildType.name
        val date = SimpleDateFormat("yyyy-MM-dd-HHmmss").format(Date())
        outputs.all {
            // 判断是否是输出 apk 类型
            if (this is com.android.build.gradle
                .internal.api.ApkVariantOutputImpl) {
                this.outputFileName = "mvvm" +
                        "_${android.defaultConfig.versionName}_${date}_${buildType}.apk"
            }
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
        dataBinding=true
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.databinding.runtime)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.gson)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.adapter.rxjava2)
    implementation(libs.databinding.runtime)
    implementation(libs.picasso)
    implementation(libs.swiperefreshlayout)
    implementation(libs.circleimageview)
    implementation(libs.room)
    implementation(libs.room.ktx)
    annotationProcessor(libs.room.compiler)
    kapt(libs.room.compiler)


}