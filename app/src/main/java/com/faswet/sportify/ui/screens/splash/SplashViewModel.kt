package com.faswet.sportify.ui.screens.splash

import com.faswet.sportify.ui.base.BaseViewModel
import com.faswet.sportify.ui.screens.splash.contract.SplashContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(

) : BaseViewModel<SplashContract.Event, SplashContract.State, SplashContract.Effect>() {
    init {
        onNavigation()
    }

    override fun handleEvents(event: SplashContract.Event) {
        when (event) {
            SplashContract.Event.OnNavigation -> onNavigation()
        }
    }

    override fun setInitialState() = SplashContract.State(
    )

    private fun onNavigation() {
        viewModelScope.launch {
            delay(2000)
            setEffect { SplashContract.Effect.Navigation.ToOnBoarding }
        }
    }
}
