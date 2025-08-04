package com.faswet.sportify.data.repositories.onboarding

import com.faswet.sportify.data.repositories.base.IBaseRepository

interface IOnboardingRepository : IBaseRepository {
    fun setAppIsOpened()
}