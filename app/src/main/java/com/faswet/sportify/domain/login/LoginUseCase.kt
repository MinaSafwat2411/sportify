package com.faswet.sportify.domain.login

import com.faswet.sportify.data.models.FirebaseResponse
import com.faswet.sportify.data.models.login.LoginRequest
import com.faswet.sportify.data.models.status.Status
import com.faswet.sportify.data.repositories.login.ILoginRepository
import com.faswet.sportify.domain.base.BaseUseCase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val mLoginRepository: ILoginRepository,
): BaseUseCase(mLoginRepository), ILoginUseCase {
    override fun login(loginRequest: LoginRequest): Flow<Status<FirebaseResponse<AuthResult?>>> {
        return mLoginRepository.login(loginRequest)
    }

    override fun setUser(user: FirebaseUser) {
        mLoginRepository.setUser(user)
    }
}