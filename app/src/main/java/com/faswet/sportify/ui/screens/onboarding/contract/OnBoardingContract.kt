package com.faswet.sportify.ui.screens.onboarding.contract

import com.faswet.sportify.ui.base.ViewEvent
import com.faswet.sportify.ui.base.ViewSideEffect
import com.faswet.sportify.ui.base.ViewState

class OnBoardingContract {
    sealed class Event : ViewEvent {
        data object OnNextClicked : Event()
        data object OnSkipClicked : Event()
    }

    data class State(
        val currentScreen: Int = 0,
        val isLastScreen: Boolean = false,
        val titleList: List<Int> = emptyList(),
        val descriptionList: List<Int> = emptyList(),
        val imageList: List<Int> = emptyList()
    ): ViewState

    sealed class Effect : ViewSideEffect{
        sealed class Navigation : Effect() {
            data object ToLogin : Navigation()
        }
    }
}