package com.faswet.sportify.data.sharedprefrences

import com.google.firebase.auth.FirebaseUser

interface IPreferencesDataSource {
    fun getIsDark(): Boolean
    fun setIsDark(value: Boolean)
    fun  getLang(): String
    fun setLang(value: String)

    fun  setAppIsOpened(value: Boolean)
    fun  getAppIsOpened(): Boolean

    fun setUser(user: FirebaseUser)
    fun getUser(): FirebaseUser
}