package com.faswet.sportify.domain.main

import com.faswet.sportify.data.repositories.main.MainRepository
import com.faswet.sportify.domain.base.BaseUseCase
import javax.inject.Inject

class MainUseCase @Inject constructor(
    private val  mainRepository: MainRepository
): BaseUseCase(mainRepository), IMainUseCase {
    override fun getIsDark(): Boolean {
        return mainRepository.getIsDark()
    }

    override fun getLang(): String {
        return mainRepository.getLang()
    }

    override fun setIsDark(isDark: Boolean) {
        mainRepository.setIsDark(isDark)
    }

    override fun setLang(lang: String) {
        mainRepository.setLang(lang)
    }
}