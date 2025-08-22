package com.faswet.sportify.data.sharedprefrences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.faswet.sportify.data.models.user.UserModel
import com.faswet.sportify.utils.constants.Constants
import com.faswet.sportify.utils.constants.Constants.SharedPreference.SHARED_PREF_NAME
import com.google.firebase.auth.FirebaseUser
import com.google.gson.Gson
import com.google.gson.GsonBuilder

class PreferencesDataSource(private val context: Context) : IPreferencesDataSource {

    private val mGson: Gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        .create()

    private val mPrefs: SharedPreferences by lazy {
        createEncryptedPrefs(context)
    }

    private val editor: SharedPreferences.Editor
        get() = mPrefs.edit()

    private fun createEncryptedPrefs(context: Context): SharedPreferences {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        return try {
            EncryptedSharedPreferences.create(
                context,
                SHARED_PREF_NAME,
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        } catch (e: Exception) {
            context.deleteSharedPreferences(SHARED_PREF_NAME)
            EncryptedSharedPreferences.create(
                context,
                SHARED_PREF_NAME,
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        }
    }

    private fun removeString(key: String) {
        editor.remove(key).commit()
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

    override fun getIsDark(): Boolean =
        getBoolean(Constants.SharedPreference.IS_DARK_MODE, false)

    override fun getLang(): String =
        getString(Constants.SharedPreference.LANGUAGE, "en") ?: "en"

    override fun setIsDark(value: Boolean) =
        setBoolean(Constants.SharedPreference.IS_DARK_MODE, value)

    override fun setLang(value: String) =
        setString(Constants.SharedPreference.LANGUAGE, value)

    override fun getAppIsOpened(): Boolean =
        getBoolean(Constants.SharedPreference.APP_IS_OPENED, false)

    override fun setAppIsOpened(value: Boolean) =
        setBoolean(Constants.SharedPreference.APP_IS_OPENED, value)

    override fun getUserUID(): String =
        getString(Constants.SharedPreference.USER_UID, "") ?: ""

    override fun setUserUID(user: FirebaseUser) =
        setString(Constants.SharedPreference.USER_UID, user.uid)

    override fun setUserData(user: UserModel) {
        val json = mGson.toJson(user)
        mPrefs.edit(commit = true) {
            putString(Constants.SharedPreference.USER_DATA, json)
        }
    }

    override fun getUserData(): UserModel? {
        val json = mPrefs.getString(Constants.SharedPreference.USER_DATA, null)
        return json?.let { mGson.fromJson(it, UserModel::class.java) }
    }
}
