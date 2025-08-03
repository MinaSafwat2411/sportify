package com.faswet.sportify.firebase

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class FirebaseService @Inject constructor(
    private val auth: FirebaseAuth
) : IFirebaseService {

    override suspend fun loginWithEmail(email: String, password: String): Result<String> {
        return Result.success("userId")
    }

    override suspend fun registerWithEmail(email: String, password: String): Result<String> {
        // TODO: Implement logic using FirebaseAuth
        return Result.success("userId")
    }

    override suspend fun sendPasswordResetEmail(email: String): Result<Unit> {
        return Result.success(Unit)
    }

    override suspend fun logout(): Result<Unit> {
        // TODO: Implement logic
        return Result.success(Unit)
    }

    override fun getCurrentUserId(): String? {
        return auth.currentUser?.uid
    }
}
