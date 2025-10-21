package com.faswet.sportify.data.repositories.splash

import com.faswet.sportify.data.models.FirebaseResponse
import com.faswet.sportify.data.models.status.Status
import com.faswet.sportify.data.models.user.UserModel
import com.faswet.sportify.data.repositories.base.IBaseRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface ISplashRepository : IBaseRepository {
    fun getAppIsOpened(): Boolean

    fun getUserUID(): String?

    fun getUserData(): Flow<Status<UserModel?>>

    fun setUserData(user: UserModel)
}