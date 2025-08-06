package com.faswet.sportify.data.models.user

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import java.util.Date

@Keep
data class UserModel(
    @SerializedName("activityHistory")
    val activityHistory: List<ActivityHistory>? = null,
    @SerializedName("birthday")
    val birthday: Date? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("favoriteSports")
    val favoriteSports: List<String>? = null,
    @SerializedName("gender")
    val gender: String? = null,
    @SerializedName("membershipPlanId")
    val membershipPlanId: String? = null,
    @SerializedName("membershipStatus")
    val membershipStatus: MembershipStatus? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("phone")
    val phone: String? = null,
    @SerializedName("pin")
    val pin: String? = null,
    @SerializedName("profilePicture")
    val profilePicture: ProfilePicture? = null,
    @SerializedName("role")
    val role: Role? = null,
    @SerializedName("uid")
    val uid: String?= null,
)