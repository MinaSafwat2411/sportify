package com.faswet.sportify.data.repositories.layout

import com.faswet.sportify.data.models.user.UserModel
import com.faswet.sportify.data.repositories.base.IBaseRepository

interface ILayoutRepository : IBaseRepository {
    fun getUserData() : UserModel?
}