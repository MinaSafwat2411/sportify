package com.faswet.sportify.domain.profile

import com.faswet.sportify.data.models.FirebaseResponse
import com.faswet.sportify.data.models.membershipplan.MemberShipPlan
import com.faswet.sportify.data.models.status.Status
import com.faswet.sportify.data.models.user.UserModel
import com.faswet.sportify.domain.base.IBaseUseCase
import kotlinx.coroutines.flow.Flow

interface IProfileUseCase : IBaseUseCase {
    fun getUserData(): UserModel
    fun setUserData(userModel: UserModel)

    fun getMemberShip(doc: String): Flow<Status<FirebaseResponse<MemberShipPlan?>>>

}