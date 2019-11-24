const val kotlinVersion = "1.3.50"

object BuildPlugins {

    object Versions {
        const val buildToolsVersion = "3.5.2"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
    const val kotlinKapt = "kotlin-kapt"

}

object AndroidSdk {
    const val min = 16
    const val compile = 28
    const val target = compile
}

object Libraries {
    private object Versions {
        const val jetpack = "1.1.0"
        const val constraintLayout = "1.1.3"
        const val ktx = "1.1.0"
        const val material = "1.0.0"
        const val recyclerView = "1.0.0"
        const val room = "2.2.1"
        const val rxJava = "2.2.14"
        const val rxKotlin = "2.4.0"
        const val rxAndroid = "2.1.1"
        const val rxPermission = "0.10.2"
        const val glide = "4.10.0"
        const val retrofit = "2.6.2"
        const val moxy = "1.7.0"
        const val dagger2 = "2.25.2"
        const val okHttpLogging = "3.12.0"
        const val okHttp = "3.12.0"
    }

    const val kotlinStdLib     = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
    const val ktxCore          = "androidx.core:core-ktx:${Versions.ktx}"

    const val appCompat        = "androidx.appcompat:appcompat:${Versions.jetpack}"
    const val recyclerView     = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val material         = "com.google.android.material:material:${Versions.material}"
    const val vectorDrawable   = "androidx.vectordrawable:vectordrawable:${Versions.jetpack}"

    const val roomRuntime      = "androidx.room:room-runtime:${Versions.room}"
    const val roomKtx          = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompiler     = "androidx.room:room-compiler:${Versions.room}"
    const val roomRxJava2      = "androidx.room:room-rxjava2:${Versions.room}"

    const val rxJava2          = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    const val rxKotlin         = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlin}"
    const val rxAndroid        = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
    const val rxPermission     = "com.github.tbruyelle:rxpermissions:${Versions.rxPermission}"

    const val glide            = "com.github.bumptech.glide:glide:${Versions.glide}"

    const val retrofit         = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitMoshi    = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val retrofitRxJava2  = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    const val okHttpLogging    = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpLogging}"
    const val okHttp           = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"

    const val moxy             = "tech.schoolhelper:moxy-x:${Versions.moxy}"
    const val moxyAppCompat    = "tech.schoolhelper:moxy-x-androidx:${Versions.moxy}"

    const val moxyCompiler     = "tech.schoolhelper:moxy-x-compiler:${Versions.moxy}"
    const val dagger2          = "com.google.dagger:dagger:${Versions.dagger2}"

    const val dagger2Compiler  = "com.google.dagger:dagger-compiler:${Versions.dagger2}"
}

object TestLibraries {
    private object Versions {
        const val junit4 = "4.12"
        const val testRunner = "1.2.0"
        const val espresso = "3.1.0"
    }
    const val junit4     = "junit:junit:${Versions.junit4}"
    const val testRunner = "androidx.test:runner:${Versions.testRunner}"
    const val espresso   = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}