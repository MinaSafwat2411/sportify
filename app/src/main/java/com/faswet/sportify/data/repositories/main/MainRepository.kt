package com.faswet.sportify.data.repositories.main

import com.faswet.sportify.data.local.ILocalDataSource
import com.faswet.sportify.data.remote.IRemoteDataSource
import com.faswet.sportify.data.repositories.base.BaseRepository
import com.faswet.sportify.data.sharedprefrences.IPreferencesDataSource
import com.faswet.sportify.di.IoDispatcher
import com.faswet.sportify.utils.connection.IConnectionUtils
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val connectionUtils: IConnectionUtils,
    private val mIRemoteDataSource: IRemoteDataSource,
    private val mILocalDataSource: ILocalDataSource,
    private val mIPreferencesDataSource: IPreferencesDataSource,
    @IoDispatcher dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseRepository(connectionUtils, mIRemoteDataSource, mIPreferencesDataSource,dispatcher),
    IMainRepository {
    override fun getIsDark(): Boolean {
        return !mIPreferencesDataSource.getIsDark()
    }

    override fun getLang(): String {
        return mIPreferencesDataSource.getLang()
    }

    override fun setLang(lang: String) {
        mIPreferencesDataSource.setLang(lang)
    }

    override fun setIsDark(isDark: Boolean) {
        mIPreferencesDataSource.setIsDark(isDark)
    }

}