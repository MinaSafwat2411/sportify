package com.faswet.sportify.data.repositories.profile

import com.faswet.sportify.data.models.FirebaseResponse
import com.faswet.sportify.data.models.membershipplan.MemberShipPlan
import com.faswet.sportify.data.models.status.Status
import com.faswet.sportify.data.models.user.UserModel
import com.faswet.sportify.data.repositories.base.IBaseRepository
import kotlinx.coroutines.flow.Flow

interface IProfileRepository: IBaseRepository {
    fun getUserData(): UserModel?
    fun setUserData(userModel: UserModel)

    fun getMemberShipPlan(doc: String):Flow<Status<FirebaseResponse<MemberShipPlan?>>>

}