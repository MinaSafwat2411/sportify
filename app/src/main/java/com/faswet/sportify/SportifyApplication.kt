package com.faswet.sportify

import android.app.Application
import android.content.Context
import com.faswet.sportify.utils.loacl.updateLocale
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SportifyApplication : Application() {
    override fun attachBaseContext(base: Context?) {
        val lang = base?.getSharedPreferences("settings", MODE_PRIVATE)
            ?.getString("lang", "en") ?: "en"

        val newBase = base?.updateLocale(lang)
        super.attachBaseContext(newBase)
    }
}