package com.faswet.sportify.data.repositories.base

import com.faswet.sportify.data.models.status.Status
import com.faswet.sportify.data.remote.IRemoteDataSource
import com.faswet.sportify.data.sharedprefrences.IPreferencesDataSource
import com.faswet.sportify.utils.connection.IConnectionUtils
import com.google.firebase.FirebaseException
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

open class BaseRepository(
    private val connectionUtils: IConnectionUtils,
    private val mIRemoteDataSource: IRemoteDataSource,
    private val mIPreferencesDataSource: IPreferencesDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : IBaseRepository {

    private val gson = GsonBuilder().serializeNulls().create()

    private val isConnected: Boolean
        get() {
            return connectionUtils.isConnected
        }

    fun <T> safeFirebaseCall(
        firebaseCall: suspend () -> T
    ): Flow<Status<T>> {
        return flow {
            if (isConnected) {
                try {
                    val response = firebaseCall.invoke()
                    emit(Status.Success(response))
                } catch (e: FirebaseException) {
                    emit(Status.Error(data = null, error = e.message ?: ""))
                } catch (e: Exception) {
                    emit(Status.Error(data = null, error = e.message ?: ""))
                }
            } else {
                emit(Status.Error(data = null, error = "No Internet Connection"))
            }
        }
    }
}