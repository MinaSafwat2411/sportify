package com.faswet.sportify.data.sharedprefrences

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.faswet.sportify.utils.constants.Constants
import com.google.gson.Gson
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.faswet.sportify.utils.constants.Constants.SharedPreference.SHARED_PREF_NAME
import androidx.core.content.edit

class PreferencesDataSource(private  val context: Context, private val mGson: Gson): IPreferencesDataSource {
    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
    private val mPrefs = EncryptedSharedPreferences.create(
        SHARED_PREF_NAME,
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    private val editor: SharedPreferences.Editor = mPrefs.edit()

    private fun removeString(key: String) {
        editor.remove(key)
        editor.commit()
    }

    private fun setString(key: String, value: String) {
        mPrefs.edit(commit = true) { putString(key, value) }
    }

    private fun setInt(key: String, value: Int) {
        mPrefs.edit { putInt(key, value) }
    }

    private fun setBoolean(key: String, value: Boolean) {
        mPrefs.edit { putBoolean(key, value) }
    }

    private fun getString(key: String, defaultValue: String): String? {
        return mPrefs.getString(key, defaultValue)
    }

    private fun getInt(key: String, defaultValue: Int): Int {
        return mPrefs.getInt(key, defaultValue)
    }
    private fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return mPrefs.getBoolean(key, defaultValue)
    }

    override fun getIsDark(): Boolean {
        return getBoolean(Constants.SharedPreference.IS_DARK_MODE, false)
    }

    override fun getLang(): String {
        return getString(Constants.SharedPreference.LANGUAGE, "en") ?: "en"
    }

    override fun setIsDark(value: Boolean) {
        setBoolean(Constants.SharedPreference.IS_DARK_MODE, value)
    }

    override fun setLang(value: String) {
        setString(Constants.SharedPreference.LANGUAGE, value)
    }
}