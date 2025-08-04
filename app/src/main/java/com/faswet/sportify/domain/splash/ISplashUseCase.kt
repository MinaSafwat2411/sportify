package com.faswet.sportify.domain.splash

import com.faswet.sportify.domain.base.IBaseUseCase

interface ISplashUseCase : IBaseUseCase {
    fun getAppIsOpened(): Boolean
}