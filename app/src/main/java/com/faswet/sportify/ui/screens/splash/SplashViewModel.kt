package com.faswet.sportify.ui.screens.splash

import com.faswet.sportify.domain.splash.SplashUseCase
import com.faswet.sportify.ui.base.BaseViewModel
import com.faswet.sportify.ui.screens.splash.contract.SplashContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val splashUseCase: SplashUseCase
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
            if (splashUseCase.getAppIsOpened()){
                if (splashUseCase.getUserUID().isNullOrEmpty().not()){
                    setEffect { SplashContract.Effect.Navigation.ToLayout }
                    return@launch
                }else{
                    setEffect { SplashContract.Effect.Navigation.ToLogin }
                    return@launch
                }
            }else{
                setEffect { SplashContract.Effect.Navigation.ToOnBoarding }
                return@launch
            }
        }
    }
}
