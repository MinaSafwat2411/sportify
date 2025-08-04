package com.faswet.sportify.domain.splash

import com.faswet.sportify.data.repositories.splash.SplashRepository
import com.faswet.sportify.domain.base.BaseUseCase
import com.faswet.sportify.domain.onboarding.IOnBoardingUseCase
import javax.inject.Inject

class SplashUseCase @Inject constructor(
    private val  splashRepository: SplashRepository
): BaseUseCase(splashRepository), ISplashUseCase {
    override fun getAppIsOpened(): Boolean {
        return splashRepository.getAppIsOpened()
    }
}