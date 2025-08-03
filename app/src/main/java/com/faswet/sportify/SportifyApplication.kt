package com.faswet.sportify

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.faswet.sportify.data.sharedprefrences.PreferencesDataSource
import com.faswet.sportify.utils.loacl.LocaleHelper
import com.faswet.sportify.utils.constants.Constants
import com.google.gson.Gson
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SportifyApplication : Application()