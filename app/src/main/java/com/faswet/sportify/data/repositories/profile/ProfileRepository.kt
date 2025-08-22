package com.faswet.sportify.data.repositories.profile

import com.faswet.sportify.data.drive.IGoogleDriveUploader
import com.faswet.sportify.data.local.ILocalDataSource
import com.faswet.sportify.data.models.user.UserModel
import com.faswet.sportify.data.remote.IRemoteDataSource
import com.faswet.sportify.data.repositories.base.BaseRepository
import com.faswet.sportify.data.sharedprefrences.IPreferencesDataSource
import com.faswet.sportify.di.IoDispatcher
import com.faswet.sportify.utils.connection.IConnectionUtils
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val connectionUtils: IConnectionUtils,
    private val mIRemoteDataSource: IRemoteDataSource,
    private val mILocalDataSource: ILocalDataSource,
    private val mIPreferencesDataSource: IPreferencesDataSource,
    private val  mIGoogleDriveUploader: IGoogleDriveUploader,
    @IoDispatcher dispatcher: CoroutineDispatcher = Dispatchers.IO
): BaseRepository(connectionUtils, mIRemoteDataSource, mIPreferencesDataSource, dispatcher), IProfileRepository {
    override fun getUserData(): UserModel? {
        return mIPreferencesDataSource.getUserData()
    }

    override fun setUserData(userModel: UserModel) {
        mIPreferencesDataSource.setUserData(userModel)
    }
}