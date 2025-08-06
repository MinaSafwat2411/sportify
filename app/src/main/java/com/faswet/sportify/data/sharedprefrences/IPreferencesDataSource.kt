package com.faswet.sportify.data.sharedprefrences

import com.faswet.sportify.data.models.user.UserModel
import com.google.firebase.auth.FirebaseUser

interface IPreferencesDataSource {
    fun getIsDark(): Boolean
    fun setIsDark(value: Boolean)
    fun  getLang(): String
    fun setLang(value: String)

    fun  setAppIsOpened(value: Boolean)
    fun  getAppIsOpened(): Boolean

    fun setUserUID(user: FirebaseUser)
    fun getUserUID(): String

    fun setUserData(user: UserModel)
    fun getUserData(): UserModel?
}