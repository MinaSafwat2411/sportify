package com.faswet.sportify.ui.screens.profile.contract

import com.faswet.sportify.data.models.user.UserModel
import com.faswet.sportify.ui.base.ViewEvent
import com.faswet.sportify.ui.base.ViewSideEffect
import com.faswet.sportify.ui.base.ViewState

class ProfileContract {
    sealed class Event : ViewEvent {
        data object OnBackClicked : Event()
    }

    data class State(
        val isLoading: Boolean = false,
        val userModel: UserModel= UserModel()
    ): ViewState

    sealed class Effect : ViewSideEffect{
        sealed class Navigation : Effect() {
            data object Back : Navigation()
        }
    }
}