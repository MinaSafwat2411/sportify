package com.faswet.sportify.data.repositories.login

import com.faswet.sportify.data.models.FirebaseResponse
import com.faswet.sportify.data.models.login.LoginRequest
import com.faswet.sportify.data.models.status.Status
import com.faswet.sportify.data.models.user.UserModel
import com.faswet.sportify.data.repositories.base.IBaseRepository
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface ILoginRepository: IBaseRepository {
    fun login(loginRequest: LoginRequest): Flow<Status<AuthResult?>>
    fun setUserUID(user: FirebaseUser)

    fun getUserData(): Flow<Status<UserModel?>>

    fun setUserData(user: UserModel)
}