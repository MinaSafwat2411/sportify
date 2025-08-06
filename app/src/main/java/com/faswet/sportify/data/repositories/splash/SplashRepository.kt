package com.faswet.sportify.data.repositories.splash

import com.faswet.sportify.data.local.ILocalDataSource
import com.faswet.sportify.data.models.FirebaseResponse
import com.faswet.sportify.data.models.status.Status
import com.faswet.sportify.data.models.user.UserModel
import com.faswet.sportify.data.remote.IRemoteDataSource
import com.faswet.sportify.data.repositories.base.BaseRepository
import com.faswet.sportify.data.sharedprefrences.IPreferencesDataSource
import com.faswet.sportify.di.IoDispatcher
import com.faswet.sportify.firebase.IFirebaseService
import com.faswet.sportify.utils.connection.IConnectionUtils
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SplashRepository @Inject constructor(
    private val mFirebaseService: IFirebaseService,
    private val connectionUtils: IConnectionUtils,
    private val mIRemoteDataSource: IRemoteDataSource,
    private val mILocalDataSource: ILocalDataSource,
    private val mIPreferencesDataSource: IPreferencesDataSource,
    @IoDispatcher dispatcher: CoroutineDispatcher = Dispatchers.IO
): BaseRepository(connectionUtils, mIRemoteDataSource, mIPreferencesDataSource, dispatcher),
    ISplashRepository {
    override fun getAppIsOpened(): Boolean {
        return mIPreferencesDataSource.getAppIsOpened()
    }

    override fun getUserUID(): String? {
        return mIPreferencesDataSource.getUserUID()
    }

    override fun getUserData(): Flow<Status<FirebaseResponse<UserModel?>>> {
        return safeFirebaseCall {
            mFirebaseService.getUserData()
        }
    }

    override fun setUserData(user: UserModel) {
        mIPreferencesDataSource.setUserData(user)
    }
}