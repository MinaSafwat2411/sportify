package com.faswet.sportify.data.sharedprefrences

interface IPreferencesDataSource {
    fun getIsDark(): Boolean
    fun setIsDark(value: Boolean)
    fun  getLang(): String
    fun setLang(value: String)
}