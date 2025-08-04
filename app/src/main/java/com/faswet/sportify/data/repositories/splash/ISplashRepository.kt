package com.faswet.sportify.data.repositories.splash

import com.faswet.sportify.data.repositories.base.IBaseRepository

interface ISplashRepository : IBaseRepository {
    fun getAppIsOpened(): Boolean
}