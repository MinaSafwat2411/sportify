package com.faswet.sportify.data.models.login

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class LoginRequest(
    val email: String,
    val password: String
): Parcelable