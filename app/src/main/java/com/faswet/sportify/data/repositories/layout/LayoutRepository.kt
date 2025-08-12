package com.faswet.sportify.data.repositories.layout

import com.faswet.sportify.data.drive.IGoogleDriveUploader
import com.faswet.sportify.data.local.ILocalDataSource
import com.faswet.sportify.data.models.user.UserModel
import com.faswet.sportify.data.remote.IRemoteDataSource
import com.faswet.sportify.data.sharedprefrences.IPreferencesDataSource
import com.faswet.sportify.di.IoDispatcher
import com.faswet.sportify.firebase.IFirebaseService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class LayoutRepository @Inject constructor(
    private val localDataSource: ILocalDataSource,
    private val remoteDataSource: IRemoteDataSource,
    private val preferencesDataSource: IPreferencesDataSource,
    private val googleDriveUploader: IGoogleDriveUploader,
    private val mFirebaseService: IFirebaseService,
    @IoDispatcher dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ILayoutRepository {
    override fun getUserData(): UserModel? {
        return  preferencesDataSource.getUserData()
    }
}