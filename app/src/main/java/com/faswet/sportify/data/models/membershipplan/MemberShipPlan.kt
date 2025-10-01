package com.faswet.sportify.data.models.membershipplan

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class MemberShipPlan(
    @SerializedName("benefits")
    val benefits : List<String>? = null,
    @SerializedName("isActive")
    val isActive : Boolean = false,
    @SerializedName("planName")
    val planName : String? = null,
    @SerializedName("price")
    val price : Int? = null,
    @SerializedName("validityPeriod")
    val validityPeriod : String? = null
): Parcelable
