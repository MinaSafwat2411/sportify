package com.faswet.sportify.ui.screens.splash

import com.faswet.sportify.data.models.user.UserModel
import com.faswet.sportify.di.MainDispatcher
import com.faswet.sportify.domain.splash.SplashUseCase
import com.faswet.sportify.ui.base.BaseViewModel
import com.faswet.sportify.ui.screens.splash.contract.SplashContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val splashUseCase: SplashUseCase,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher,
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
        val handler = CoroutineExceptionHandler { _, exception ->
            viewModelScope.launch {
                setState { copy(isLoading = false) }
                exception.printStackTrace()
            }
        }
        viewModelScope.launch(mainDispatcher + handler) {
            if (splashUseCase.getAppIsOpened()){
                if (splashUseCase.getUserUID().isNullOrEmpty().not()){
                    splashUseCase.getUserData().onStart {
                        setState { copy(isLoading = true) }
                    }.onCompletion {
                        setState { copy(isLoading = false) }
                    }.catch {
                        setState { copy(errorMessage = it.message) }
                        setEffect { SplashContract.Effect.Navigation.ToLogin }
                        return@catch
                    }.collect {status ->
                        if (status.isSuccess()){
                            splashUseCase.setUserData(status.data?: UserModel())
                            setEffect { SplashContract.Effect.Navigation.ToLayout }
                            return@collect
                        }else{
                            setEffect { SplashContract.Effect.Navigation.ToLogin }
                            return@collect
                        }
                    }
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
