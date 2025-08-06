package com.faswet.sportify.domain.splash

import com.faswet.sportify.data.models.FirebaseResponse
import com.faswet.sportify.data.models.status.Status
import com.faswet.sportify.data.models.user.UserModel
import com.faswet.sportify.domain.base.IBaseUseCase
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface ISplashUseCase : IBaseUseCase {
    fun getAppIsOpened(): Boolean
    fun getUserUID(): String?

    fun getUserData(): Flow<Status<FirebaseResponse<UserModel?>>>

    fun setUserData(user: UserModel)
}