package com.faswet.sportify.domain.profile

import com.faswet.sportify.data.models.user.UserModel
import com.faswet.sportify.domain.base.IBaseUseCase

interface IProfileUseCase : IBaseUseCase {
    fun getUserData(): UserModel
    fun setUserData(userModel: UserModel)
}