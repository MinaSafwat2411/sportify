package com.faswet.sportify.data.repositories.login

import com.faswet.sportify.data.models.FirebaseResponse
import com.faswet.sportify.data.models.login.LoginRequest
import com.faswet.sportify.data.models.status.Status
import com.faswet.sportify.data.models.user.UserModel
import com.faswet.sportify.data.remote.IRemoteDataSource
import com.faswet.sportify.data.repositories.base.BaseRepository
import com.faswet.sportify.data.sharedprefrences.IPreferencesDataSource
import com.faswet.sportify.di.IoDispatcher
import com.faswet.sportify.firebase.IFirebaseService
import com.faswet.sportify.utils.connection.IConnectionUtils
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val mConnectionUtils: IConnectionUtils,
    private val mRemoteDataSource: IRemoteDataSource,
    private val mPreferencesDataSource: IPreferencesDataSource,
    private val mFirebaseService: IFirebaseService,
    @IoDispatcher dispatcher: CoroutineDispatcher = Dispatchers.IO
): BaseRepository(mConnectionUtils, mRemoteDataSource, mPreferencesDataSource, dispatcher),
    ILoginRepository {
    override fun login(loginRequest: LoginRequest): Flow<Status<AuthResult?>> {
        return safeFirebaseCall {
            mFirebaseService.loginWithEmail(loginRequest)
        }
    }

    override fun setUserUID(user: FirebaseUser) {
        mPreferencesDataSource.setUserUID(user)
    }

    override fun getUserData(): Flow<Status<UserModel?>> {
        return safeFirebaseCall {
            mFirebaseService.getUserData()
        }
    }

    override fun setUserData(user: UserModel) {
        mPreferencesDataSource.setUserData(user)
    }
}