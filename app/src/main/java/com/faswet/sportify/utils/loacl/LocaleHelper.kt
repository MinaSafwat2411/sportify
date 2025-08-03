package com.faswet.sportify.utils.loacl

import android.content.Context
import android.content.res.Configuration
import android.os.LocaleList
import java.util.Locale


object LocaleHelper {
    private fun updateResources(context: Context, language: String): Context {
        val appLocale = Locale(language)
        val numberLocale = Locale("en")
        Locale.setDefault (appLocale)
        val config = Configuration().apply {
            setLocale(appLocale)
            setLayoutDirection (appLocale)
            setLocales (LocaleList(appLocale))
            Locale.setDefault (Locale.Category.FORMAT, numberLocale)
        }
        val updatedContext =
            context.createConfigurationContext(config)
        context.resources.updateConfiguration (config, context.resources.displayMetrics)
        return updatedContext
    }

    fun setLocale(context: Context, language: String): Context {
        return updateResources(context, language)
    }
}