package com.faswet.sportify.domain.splash

import com.faswet.sportify.domain.base.IBaseUseCase
import com.google.firebase.auth.FirebaseUser

interface ISplashUseCase : IBaseUseCase {
    fun getAppIsOpened(): Boolean
    fun getUserUID(): String?
}