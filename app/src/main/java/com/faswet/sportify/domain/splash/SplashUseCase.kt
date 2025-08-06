package com.faswet.sportify.domain.splash

import com.faswet.sportify.data.models.FirebaseResponse
import com.faswet.sportify.data.models.status.Status
import com.faswet.sportify.data.models.user.UserModel
import com.faswet.sportify.data.repositories.splash.SplashRepository
import com.faswet.sportify.domain.base.BaseUseCase
import com.faswet.sportify.domain.onboarding.IOnBoardingUseCase
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SplashUseCase @Inject constructor(
    private val  splashRepository: SplashRepository
): BaseUseCase(splashRepository), ISplashUseCase {
    override fun getAppIsOpened(): Boolean {
        return splashRepository.getAppIsOpened()
    }

    override fun getUserUID(): String? {
        return splashRepository.getUserUID()
    }

    override fun getUserData(): Flow<Status<FirebaseResponse<UserModel?>>> {
        return splashRepository.getUserData()
    }

    override fun setUserData(user: UserModel) {
        splashRepository.setUserData(user)
    }
}