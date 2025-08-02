package com.faswet.sportify.firebase

interface FirebaseService {
    suspend fun loginWithEmail(email: String, password: String): Result<String>

    suspend fun registerWithEmail(email: String, password: String): Result<String>

    suspend fun sendPasswordResetEmail(email: String): Result<Unit>

    suspend fun logout(): Result<Unit>

    fun getCurrentUserId(): String?
}