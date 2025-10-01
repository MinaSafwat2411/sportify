package com.faswet.sportify.data.repositories.profile

import com.faswet.sportify.data.drive.IGoogleDriveUploader
import com.faswet.sportify.data.local.ILocalDataSource
import com.faswet.sportify.data.models.FirebaseResponse
import com.faswet.sportify.data.models.membershipplan.MemberShipPlan
import com.faswet.sportify.data.models.status.Status
import com.faswet.sportify.data.models.user.UserModel
import com.faswet.sportify.data.remote.IRemoteDataSource
import com.faswet.sportify.data.repositories.base.BaseRepository
import com.faswet.sportify.data.sharedprefrences.IPreferencesDataSource
import com.faswet.sportify.di.IoDispatcher
import com.faswet.sportify.firebase.IFirebaseService
import com.faswet.sportify.utils.connection.IConnectionUtils
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val connectionUtils: IConnectionUtils,
    private val mFirebaseService: IFirebaseService,
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

    override fun getMemberShipPlan(doc: String): Flow<Status<FirebaseResponse<MemberShipPlan?>>> {
        return safeFirebaseCall {
            mFirebaseService.getMemberShip(doc)
        }
    }
}