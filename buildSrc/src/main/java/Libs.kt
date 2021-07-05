object Libs {

    const val gradle = "com.android.tools.build:gradle:" + Versions.gradle_version

    const val kotlin_gradle_plugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:" + Versions.org_jetbrains_kotlin

    const val kotlin_stdlib_jdk7 =
        "org.jetbrains.kotlin:kotlin-stdlib-jdk7:" + Versions.org_jetbrains_kotlin

    const val androidx_app_compat =
        "androidx.appcompat:appcompat:" + Versions.androidx_app_compat_version

    const val androidx_core_ktx =
        "androidx.core:core-ktx:" + Versions.androidx_core_ktx

    const val kotlinx_coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:" +
            Versions.org_jetbrains_kotlinx_kotlinx_coroutines

    const val kotlinx_coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:" +
            Versions.org_jetbrains_kotlinx_kotlinx_coroutines

    const val kotlinx_coroutines_test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:" +
            Versions.org_jetbrains_kotlinx_kotlinx_coroutines

    const val detekt_formatting = "io.gitlab.arturbosch.detekt:detekt-formatting:" +
            Versions.io_gitlab_arturbosch_detekt

    const val detekt_gradle_plugin = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:" +
            Versions.io_gitlab_arturbosch_detekt

    const val converter_moshi = "com.squareup.retrofit2:converter-moshi:" +
            Versions.com_squareup_retrofit2

    const val retrofit = "com.squareup.retrofit2:retrofit:" +
            Versions.com_squareup_retrofit2

    const val logging_interceptor = "com.squareup.okhttp3:logging-interceptor:" +
            Versions.com_squareup_okhttp3

    const val navigation_fragment_ktx = "androidx.navigation:navigation-fragment-ktx:" +
            Versions.androidx_navigation

    const val navigation_safe_args_gradle_plugin =
        "androidx.navigation:navigation-safe-args-gradle-plugin:" + Versions.androidx_navigation

    const val navigation_ui_ktx = "androidx.navigation:navigation-ui-ktx:" +
            Versions.androidx_navigation

    const val lifecycle_common_java8 = "androidx.lifecycle:lifecycle-common-java8:" +
            Versions.androidx_lifecycle

    const val lifecycle_livedata_ktx =
        "androidx.lifecycle:lifecycle-livedata-ktx:" + Versions.androidx_lifecycle

    const val activity_ktx = "androidx.activity:activity-ktx:" + Versions.androidx_activity_ktx

    const val fragment_ktx = "androidx.fragment:fragment-ktx:" + Versions.androidx_fragment_ktx

    const val lifecycle_process =
        "androidx.lifecycle:lifecycle-process:" + Versions.androidx_lifecycle

    const val lifecycle_viewmodel_ktx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:" + Versions.androidx_lifecycle

    const val moshi = "com.squareup.moshi:moshi:" + Versions.com_squareup_moshi

    const val moshi_kotlin_codegen = "com.squareup.moshi:moshi-kotlin-codegen:" +
            Versions.com_squareup_moshi

    const val dagger = "com.google.dagger:dagger:" + Versions.com_google_dagger

    const val dagger_android_processor = "com.google.dagger:dagger-android-processor:" +
            Versions.com_google_dagger

    const val dagger_android_support = "com.google.dagger:dagger-android-support:" +
            Versions.com_google_dagger

    const val dagger_compiler = "com.google.dagger:dagger-compiler:" +
            Versions.com_google_dagger

    const val androidx_test_rules = "androidx.test:rules:" + Versions.androidx_test

    const val constraintlayout = "androidx.constraintlayout:constraintlayout:" +
            Versions.constraintlayout

    const val core_testing = "androidx.arch.core:core-testing:" + Versions.core_testing

    const val lint_gradle = "com.android.tools.lint:lint-gradle:" + Versions.lint_gradle

    const val robolectric = "org.robolectric:robolectric:" + Versions.robolectric

    const val material = "com.google.android.material:material:" + Versions.material

    const val timber = "com.jakewharton.timber:timber:" + Versions.timber

    const val glide = "com.github.bumptech.glide:glide:" + Versions.glide

    const val mockk = "io.mockk:mockk:" + Versions.mockk

    const val mockk_android = "io.mockk:mockk-android:" + Versions.mockk

    const val junit_junit = "junit:junit:" + Versions.junit_junit

    const val androidx_test_ext_junit = "androidx.test.ext:junit:" +
            Versions.androidx_test_ext_junit

    const val assertj_android = "com.squareup.assertj:assertj-android:" +
            Versions.assertj_android_version

    const val assertj_core = "org.assertj:assertj-core:" +
            Versions.assertj_core_version

    const val androidx_test_espresso_web = "androidx.test.espresso:espresso-web:" + Versions.androidx_espresso_web

    const val espresso_idling_resource_concurrent = "com.android.support.test.espresso.idling:idling-concurrent:" + Versions.concurrent_espresso_idling

    const val espresso_idling_resource_okhttp = "com.jakewharton.espresso:okhttp3-idling-resource:" + Versions.okhttp3_espresso_idling

    const val androidx_test_runner = "androidx.test:runner:" + Versions.androidx_test

    const val objenesis = "org.objenesis:objenesis:" + Versions.objenesis

    const val barista = "com.schibsted.spain:barista:" + Versions.barista

    const val dynatracePlugin = "com.dynatrace.tools.android:gradle-plugin:" + Versions.dynatrace

    const val leakcanary = "com.squareup.leakcanary:leakcanary-android:" + Versions.leakcanary

    const val rxandroid = "io.reactivex.rxjava2:rxandroid:" + Versions.rxandroid

    const val rxjava = "io.reactivex.rxjava2:rxjava:" + Versions.rxjava

    const val room = "androidx.room:room-runtime:" + Versions.room

    const val room_compiler = "androidx.room:room-compiler:" + Versions.room

    const val room_ktx = "androidx.room:room-ktx:" + Versions.room

    const val orchestrator = "androidx.test:orchestrator:" + Versions.orchestrator

    const val jacoco = "org.jacoco:org.jacoco.core:" + Versions.jacoco
    const val vision = "com.google.android.gms:play-services-vision:" + Versions.vision

    const val swipe_to_refresh =
        "androidx.swiperefreshlayout:swiperefreshlayout:" + Versions.swipe_to_refresh

    const val transition = "androidx.transition:transition:" + Versions.transition

    const val play_core = "com.google.android.play:core:" + Versions.play_core

    const val ktlint_gradle = "org.jlleitschuh.gradle:ktlint-gradle:" + Versions.ktlint_gradle

    const val analytics = "com.ford:analytics:" + Versions.analytics_version

    const val analytics_amplitude = "com.ford:analytics-amplitude:" + Versions.analytics_amplitude_version

    const val analytics_adobe = "com.ford:analytics-adobe:" + Versions.analytics_version

    const val amplitude = "com.amplitude:android-sdk:" + Versions.amplitude_version
}
