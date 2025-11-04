package com.faswet.sportify.ui.screens.layout.contract

import com.faswet.sportify.data.models.events.EventResponse
import com.faswet.sportify.data.models.user.UserModel
import com.faswet.sportify.ui.base.ViewEvent
import com.faswet.sportify.ui.base.ViewSideEffect
import com.faswet.sportify.ui.base.ViewState

class LayoutContract {
    sealed class Event : ViewEvent {
        data class OnScreenChanged(val screen: Int) : Event()
        data class OnBottomNavClicked(val screen: Int) : Event()

        data object OnSettingsClicked : Event()

        data object OnProfileClicked : Event()

        data object GetData : Event()
    }
    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            object ToSettings : Navigation()
            object ToProfile : Navigation()
        }
    }
    data class State(
        val currentScreen: Int = 0,
        val userModel: UserModel? = null,
        val avatar: Int = 0,
        val isLoading: Boolean = false,
        val errorMessage: String? = null,
        val events : List<EventResponse> = emptyList()
    ): ViewState
}