package com.faswet.sportify.firebase

import android.util.Log
import com.faswet.sportify.data.models.FirebaseResponse
import com.faswet.sportify.data.models.booking.BookingResponse
import com.faswet.sportify.data.models.events.EventResponse
import com.faswet.sportify.data.models.login.LoginRequest
import com.faswet.sportify.data.models.membershipplan.MemberShipPlan
import com.faswet.sportify.data.models.user.ProfilePicture
import com.faswet.sportify.data.models.user.UserModel
import com.faswet.sportify.utils.constants.Constants
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
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
                        )
                    ) { cause, _, _ -> continuation.cancel() }
                } else {
                    val exception = task.exception
                    continuation.resume(
                        FirebaseResponse(
                            status = false,
                            data = null,
                            message = exception?.message ?: "Unknown error",
                        )
                    ) { cause, _, _ -> continuation.cancel() }
                }
            }
        }
    }

    override suspend fun logout(): FirebaseResponse<Boolean> {
        return suspendCancellableCoroutine { continuation ->
            firebaseAuth.signOut()
            continuation.resume(
                FirebaseResponse(
                    status = true,
                    data = true,
                    message = "Success",
                )
            ) { cause, _, _ -> continuation.cancel() }
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
                        )
                    ) { cause, _, _ -> continuation.cancel() }
                } else {
                    val exception = task.exception
                    continuation.resume(
                        FirebaseResponse(
                            status = false,
                            data = null,
                            message = exception?.message ?: "Unknown error",
                        )
                    ) { cause, _, _ -> continuation.cancel() }
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
                    )
                ) { cause, _, _ -> continuation.cancel() }
            } else {
                continuation.resume(
                    FirebaseResponse(
                        status = false,
                        data = null,
                        message = "User not found",
                    )
                ) { cause, _, _ -> continuation.cancel() }
            }

        }
    }

    override suspend fun getUserData(): FirebaseResponse<UserModel?> {
        return suspendCancellableCoroutine { continuation ->
            val currentUser = firebaseAuth.currentUser
            if (currentUser == null) {
                continuation.resume(
                    FirebaseResponse(
                        status = false,
                        data = null,
                        message = "User not authenticated"
                    )
                ) { cause, _, _ -> continuation.cancel() }
                return@suspendCancellableCoroutine
            }

            firestore.collection(Constants.FireBaseCollections.users).document(currentUser.uid).get()
                .addOnSuccessListener { document ->
                    val user = document.toObject(UserModel::class.java)?.copy(
                        profilePicture = (document.get("profilePicture") as? Map<*, *>)?.let {
                            ProfilePicture(
                                isUploaded = it["isUploaded"] as? Boolean ?: false,
                                profileId = (it["profileId"] as? Number)?.toInt() ?: 0,
                                profileUrl = it["profileUrl"] as? String ?: ""
                            )
                        } ?: ProfilePicture()
                    )
                    continuation.resume(
                        FirebaseResponse(
                            status = true,
                            data = user,
                            message = "Success"
                        )
                    ) { cause, _, _ -> continuation.cancel() }
                }
        }
    }

    override suspend fun getMemberShip(doc: String): FirebaseResponse<MemberShipPlan?> {
        return suspendCancellableCoroutine { continuation ->
            val currentUser = firebaseAuth.currentUser
            if (currentUser == null) {
                continuation.resume(
                    FirebaseResponse(
                        status = false,
                        data = null,
                        message = "User not authenticated"
                    )
                ) { cause, _, _ -> continuation.cancel() }
                return@suspendCancellableCoroutine
            }

            firestore.collection(Constants.FireBaseCollections.memberShipPlans).document(doc).get()
                .addOnSuccessListener { document ->
                    val memberShip = document.toObject(MemberShipPlan::class.java)
                    continuation.resume(
                        FirebaseResponse(
                            status = true,
                            data = memberShip,
                            message = "Success"
                        )
                    ) { cause, _, _ -> continuation.cancel() }
                }
        }
    }

    override fun getAllEvents(): Flow<FirebaseResponse<List<EventResponse>>> = callbackFlow {
        val listener = firestore.collection(Constants.FireBaseCollections.events)
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    trySend(
                        FirebaseResponse(
                            status = false,
                            data = null,
                            message = e.message ?: "Unknown Firestore error"
                        )
                    )
                    return@addSnapshotListener
                }

                if (snapshot != null) {
                    val events = snapshot.toObjects(EventResponse::class.java)
                    trySend(
                        FirebaseResponse(
                            status = true,
                            data = events,
                            message = "Success"
                        )
                    )
                } else {
                    trySend(
                        FirebaseResponse(
                            status = false,
                            data = null,
                            message = "Snapshot is null"
                        )
                    )
                }
            }
        awaitClose { listener.remove() }
    }

    override fun getAllBooking() : Flow<FirebaseResponse<List<BookingResponse>>> = callbackFlow {
        val listener = firestore.collection(Constants.FireBaseCollections.booking)
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    trySend(
                        FirebaseResponse(
                            status = false,
                            data = null,
                            message = e.message ?: "Unknown Firestore error"
                        )
                    )
                    return@addSnapshotListener
                }

                if (snapshot != null) {
                    val events = snapshot.toObjects(BookingResponse::class.java)
                    trySend(
                        FirebaseResponse(
                            status = true,
                            data = events,
                            message = "Success"
                        )
                    )
                } else {
                    trySend(
                        FirebaseResponse(
                            status = false,
                            data = null,
                            message = "Snapshot is null"
                        )
                    )
                }
            }
        awaitClose { listener.remove() }
    }

}
