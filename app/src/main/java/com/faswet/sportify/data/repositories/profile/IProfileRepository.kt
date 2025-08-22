package com.faswet.sportify.data.repositories.profile

import com.faswet.sportify.data.models.user.UserModel
import com.faswet.sportify.data.repositories.base.IBaseRepository

interface IProfileRepository: IBaseRepository {
    fun getUserData(): UserModel?
    fun setUserData(userModel: UserModel)

}