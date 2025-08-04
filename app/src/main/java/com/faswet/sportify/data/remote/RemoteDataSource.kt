package com.faswet.sportify.data.remote

import com.faswet.sportify.data.models.FirebaseResponse
import com.faswet.sportify.data.models.login.LoginRequest
import com.faswet.sportify.firebase.IFirebaseService
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

class RemoteDataSource(private val mFirebaseService: IFirebaseService): IRemoteDataSource {
    override suspend fun getCurrentUserId(): FirebaseResponse<FirebaseUser?> {
        return mFirebaseService.getCurrentUserId()
    }

    override suspend fun loginWithEmail(loginRequest: LoginRequest): FirebaseResponse<AuthResult?> {
        return mFirebaseService.loginWithEmail(loginRequest)
    }

    override suspend fun logout(): FirebaseResponse<Nothing> {
        return mFirebaseService.logout()
    }

    override suspend fun registerWithEmail(loginRequest: LoginRequest): FirebaseResponse<AuthResult?> {
        return mFirebaseService.registerWithEmail(loginRequest)
    }

    override suspend fun sendPasswordResetEmail(email: String): FirebaseResponse<Nothing> {
        return mFirebaseService.sendPasswordResetEmail(email)
    }
}