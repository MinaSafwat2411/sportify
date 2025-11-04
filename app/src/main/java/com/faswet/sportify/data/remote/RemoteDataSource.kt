package com.faswet.sportify.data.remote

import com.faswet.sportify.data.models.FirebaseResponse
import com.faswet.sportify.data.models.events.EventResponse
import com.faswet.sportify.data.models.login.LoginRequest
import com.faswet.sportify.data.models.membershipplan.MemberShipPlan
import com.faswet.sportify.data.models.user.UserModel
import com.faswet.sportify.firebase.IFirebaseService
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

class RemoteDataSource(private val mFirebaseService: IFirebaseService): IRemoteDataSource {
    override suspend fun getCurrentUserId(): FirebaseResponse<FirebaseUser?> {
        return mFirebaseService.getCurrentUserId()
    }

    override suspend fun loginWithEmail(loginRequest: LoginRequest): FirebaseResponse<AuthResult?> {
        return mFirebaseService.loginWithEmail(loginRequest)
    }

    override suspend fun logout(): FirebaseResponse<Boolean> {
        return mFirebaseService.logout()
    }

    override suspend fun registerWithEmail(loginRequest: LoginRequest): FirebaseResponse<AuthResult?> {
        return mFirebaseService.registerWithEmail(loginRequest)
    }

    override suspend fun sendPasswordResetEmail(email: String): FirebaseResponse<Nothing> {
        return mFirebaseService.sendPasswordResetEmail(email)
    }

    override suspend fun getUserData(): FirebaseResponse<UserModel?> {
        return mFirebaseService.getUserData()
    }

    override suspend fun getMemberShip(doc: String): FirebaseResponse<MemberShipPlan?> {
        return mFirebaseService.getMemberShip(doc)
    }

    override fun getAllEvents(): Flow<FirebaseResponse<List<EventResponse>>> {
        return mFirebaseService.getAllEvents()
    }
}