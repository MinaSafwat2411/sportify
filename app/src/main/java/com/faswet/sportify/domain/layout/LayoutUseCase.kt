package com.faswet.sportify.domain.layout

import com.faswet.sportify.data.models.user.UserModel
import com.faswet.sportify.data.repositories.layout.ILayoutRepository
import com.faswet.sportify.domain.base.BaseUseCase
import javax.inject.Inject

class LayoutUseCase @Inject constructor(
    private val layoutRepository: ILayoutRepository,
): BaseUseCase(layoutRepository), ILayoutUseCase {
    override fun getUserData(): UserModel? {
        return layoutRepository.getUserData()
    }
}