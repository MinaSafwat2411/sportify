package com.faswet.sportify.domain.layout

import com.faswet.sportify.data.models.user.UserModel
import com.faswet.sportify.domain.base.IBaseUseCase

interface ILayoutUseCase : IBaseUseCase {
    fun getUserData() : UserModel?
}