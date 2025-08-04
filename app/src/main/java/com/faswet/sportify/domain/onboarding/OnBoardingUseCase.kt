package com.faswet.sportify.domain.onboarding

import com.faswet.sportify.data.repositories.onboarding.OnboardingRepository
import com.faswet.sportify.domain.base.BaseUseCase
import javax.inject.Inject

class OnBoardingUseCase @Inject constructor(
    private val  onboardingRepository: OnboardingRepository
): BaseUseCase(onboardingRepository), IOnBoardingUseCase {
    override fun setAppIsOpened() {
        onboardingRepository.setAppIsOpened()
    }
}