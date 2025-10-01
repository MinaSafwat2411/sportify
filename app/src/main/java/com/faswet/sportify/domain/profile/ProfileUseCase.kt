package com.faswet.sportify.domain.profile

import com.faswet.sportify.data.models.FirebaseResponse
import com.faswet.sportify.data.models.membershipplan.MemberShipPlan
import com.faswet.sportify.data.models.status.Status
import com.faswet.sportify.data.models.user.UserModel
import com.faswet.sportify.data.repositories.profile.IProfileRepository
import com.faswet.sportify.domain.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileUseCase @Inject constructor(
    private val  iProfileRepository: IProfileRepository
): BaseUseCase(iProfileRepository), IProfileUseCase {
    override fun getUserData(): UserModel {
        return iProfileRepository.getUserData()?: UserModel()
    }

    override fun setUserData(userModel: UserModel) {
        iProfileRepository.setUserData(userModel)
    }

    override fun getMemberShip(doc: String): Flow<Status<FirebaseResponse<MemberShipPlan?>>> {
        return iProfileRepository.getMemberShipPlan(doc)
    }
}