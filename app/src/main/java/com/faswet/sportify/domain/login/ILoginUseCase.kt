package com.faswet.sportify.domain.login

import com.faswet.sportify.data.models.FirebaseResponse
import com.faswet.sportify.data.models.login.LoginRequest
import com.faswet.sportify.data.models.status.Status
import com.faswet.sportify.domain.base.IBaseUseCase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface ILoginUseCase: IBaseUseCase {
    fun login(loginRequest: LoginRequest): Flow<Status<FirebaseResponse<AuthResult?>>>
    fun setUser(user: FirebaseUser)
}