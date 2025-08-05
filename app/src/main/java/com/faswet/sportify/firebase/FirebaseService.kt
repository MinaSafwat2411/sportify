package com.faswet.sportify.firebase

import com.faswet.sportify.data.models.FirebaseResponse
import com.faswet.sportify.data.models.login.LoginRequest
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class FirebaseService @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val storage: FirebaseStorage,
    private val database: FirebaseDatabase,
    private val remoteConfig: FirebaseRemoteConfig,
    private val firebaseMessaging: FirebaseMessaging,
) : IFirebaseService {

    override suspend fun loginWithEmail(loginRequest: LoginRequest): FirebaseResponse<AuthResult?> {
        return suspendCancellableCoroutine { continuation ->
            val task =
                firebaseAuth.signInWithEmailAndPassword(loginRequest.email, loginRequest.password)
            task.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val result = task.result
                    continuation.resume(
                        FirebaseResponse(
                            status = true,
                            data = result,
                            message = "Success",
                        )
                    )
                } else {
                    val exception = task.exception
                    continuation.resume(
                        FirebaseResponse(
                            status = false,
                            data = null,
                            message = exception?.message ?: "Unknown error",
                        )
                    )
                }
            }
        }
    }

    override suspend fun registerWithEmail(
        loginRequest: LoginRequest
    ): FirebaseResponse<AuthResult?> {
        return suspendCancellableCoroutine { continuation ->
            val task =
                firebaseAuth.createUserWithEmailAndPassword(
                    loginRequest.email,
                    loginRequest.password
                )
            task.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val result = task.result
                    continuation.resume(
                        FirebaseResponse(
                            status = true,
                            data = result,
                            message = "Success",
                        ),
                        onCancellation = {
                            continuation.cancel()
                        }
                    )
                } else {
                    val exception = task.exception
                    continuation.resume(
                        FirebaseResponse(
                            status = false,
                            data = null,
                            message = exception?.message ?: "Unknown error",
                        ),
                        onCancellation = {
                            continuation.cancel()
                        }
                    )
                }
            }
        }
    }

    override suspend fun logout(): FirebaseResponse<Nothing> {
        return suspendCancellableCoroutine { continuation ->
            firebaseAuth.signOut()
            continuation.resume(
                FirebaseResponse(
                    status = true,
                    data = null,
                    message = "Success",
                ),
                onCancellation = {
                    continuation.cancel()
                }
            )
        }
    }


    override suspend fun sendPasswordResetEmail(email: String): FirebaseResponse<Nothing> {
        return suspendCancellableCoroutine { continuation ->
            val task = firebaseAuth.sendPasswordResetEmail(email)
            task.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    continuation.resume(
                        FirebaseResponse(
                            status = true,
                            data = null,
                            message = "Success",
                        ),
                        onCancellation = {
                            continuation.cancel()
                        }
                    )
                } else {
                    val exception = task.exception
                    continuation.resume(
                        FirebaseResponse(
                            status = false,
                            data = null,
                            message = exception?.message ?: "Unknown error",
                        ),
                        onCancellation = {
                            continuation.cancel()
                        })
                }
            }

        }
    }

    override suspend fun getCurrentUserId(): FirebaseResponse<FirebaseUser?> {
        return suspendCancellableCoroutine { continuation ->
            val currentUser = firebaseAuth.currentUser
            if (currentUser != null) {
                continuation.resume(
                    FirebaseResponse(
                        status = true,
                        data = currentUser,
                        message = "Success",
                    ),
                    onCancellation = {
                        continuation.cancel()
                    }
                )
            } else {
                continuation.resume(
                    FirebaseResponse(
                        status = false,
                        data = null,
                        message = "User not found",
                    ),
                    onCancellation = {
                        continuation.cancel()
                    }
                )
            }

        }
    }
}
