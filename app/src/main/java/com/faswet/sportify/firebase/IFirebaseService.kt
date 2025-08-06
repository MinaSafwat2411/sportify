package com.faswet.sportify.firebase

import com.faswet.sportify.data.models.FirebaseResponse
import com.faswet.sportify.data.models.login.LoginRequest
import com.faswet.sportify.data.models.user.UserModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

interface IFirebaseService {
    suspend fun loginWithEmail(loginRequest: LoginRequest): FirebaseResponse<AuthResult?>

    suspend fun registerWithEmail(loginRequest: LoginRequest): FirebaseResponse<AuthResult?>

    suspend fun sendPasswordResetEmail(email: String): FirebaseResponse<Nothing>

    suspend fun logout(): FirebaseResponse<Nothing>

    suspend fun getCurrentUserId(): FirebaseResponse<FirebaseUser?>

    suspend fun getUserData(): FirebaseResponse<UserModel?>
}