package com.faswet.sportify.ui.screens.layout

import com.faswet.sportify.di.MainDispatcher
import com.faswet.sportify.domain.layout.ILayoutUseCase
import com.faswet.sportify.ui.base.BaseViewModel
import com.faswet.sportify.ui.screens.layout.contract.LayoutContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class LayoutViewModel @Inject constructor(
    private val layoutUseCase: ILayoutUseCase,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher,
) : BaseViewModel<LayoutContract.Event, LayoutContract.State, LayoutContract.Effect>() {
    init {
        setState { copy(userModel = layoutUseCase.getUserData()) }
    }
    override fun handleEvents(event: LayoutContract.Event) {
        when (event) {
            is LayoutContract.Event.OnScreenChanged -> {
                setState { copy(currentScreen = event.screen) }
            }
            is LayoutContract.Event.OnBottomNavClicked -> {
                setState { copy(currentScreen = event.screen) }
            }
        }
    }

    override fun setInitialState() = LayoutContract.State(
        currentScreen = 0,
        userModel = null
    )
}