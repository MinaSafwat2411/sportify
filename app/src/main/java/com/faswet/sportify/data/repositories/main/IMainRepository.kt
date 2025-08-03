package com.faswet.sportify.data.repositories.main

import com.faswet.sportify.data.repositories.base.IBaseRepository

interface IMainRepository : IBaseRepository {
    fun getIsDark(): Boolean
    fun getLang(): String
    fun setLang(lang: String)
    fun setIsDark(isDark: Boolean)
}