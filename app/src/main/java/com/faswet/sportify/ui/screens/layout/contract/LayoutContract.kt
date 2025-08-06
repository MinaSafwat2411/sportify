package com.faswet.sportify.ui.screens.layout.contract

import com.faswet.sportify.ui.base.ViewEvent
import com.faswet.sportify.ui.base.ViewSideEffect
import com.faswet.sportify.ui.base.ViewState
import com.faswet.sportify.ui.screens.onboarding.contract.OnBoardingContract.Event

class LayoutContract {
    sealed class Event : ViewEvent {
        data class OnScreenChanged(val screen: Int) : Event()
        data class OnBottomNavClicked(val screen: Int) : Event()
    }
    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
        }
    }
    data class State(
        val currentScreen: Int = 0,
    ): ViewState
}