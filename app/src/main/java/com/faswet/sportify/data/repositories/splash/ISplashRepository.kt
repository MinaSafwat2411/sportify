package com.faswet.sportify.data.repositories.splash

import com.faswet.sportify.data.repositories.base.IBaseRepository
import com.google.firebase.auth.FirebaseUser

interface ISplashRepository : IBaseRepository {
    fun getAppIsOpened(): Boolean

    fun getUserUID(): String?
}