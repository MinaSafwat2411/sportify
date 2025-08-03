package com.faswet.sportify.domain.main

import com.faswet.sportify.domain.base.IBaseUseCase

interface IMainUseCase : IBaseUseCase {
    fun getLang():String

    fun getIsDark(): Boolean

    fun setIsDark(isDark: Boolean)

    fun setLang(lang: String)
}