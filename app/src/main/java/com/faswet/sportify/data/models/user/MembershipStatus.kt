package com.faswet.sportify.data.models.user

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import java.util.Date

@Keep
data class MembershipStatus(
    @SerializedName("expiredDate")
    val expiredDate: Date? = null,
    @SerializedName("membershipStatusName")
    val membershipStatusName: String? = null,
    @SerializedName("membershipStatusValue")
    val membershipStatusValue: Int? = null,
    @SerializedName("period")
    val period: String? = null
)
