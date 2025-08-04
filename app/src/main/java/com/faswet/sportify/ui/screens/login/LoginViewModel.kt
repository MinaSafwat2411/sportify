package com.faswet.sportify.ui.screens.login

import com.faswet.sportify.data.models.login.LoginRequest
import com.faswet.sportify.di.MainDispatcher
import com.faswet.sportify.domain.login.ILoginUseCase
import com.faswet.sportify.ui.base.BaseViewModel
import com.faswet.sportify.ui.screens.login.contract.LoginContract
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: ILoginUseCase,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher,
) : BaseViewModel<LoginContract.Event, LoginContract.State,LoginContract.Effect>() {
    override fun handleEvents(event: LoginContract.Event) {
        when (event) {
            is LoginContract.Event.Login -> onLogin(event.email, event.password)
        }
    }

    override fun setInitialState() = LoginContract.State()

    private fun onLogin(email: String, password: String){
        val handler = CoroutineExceptionHandler { _, exception ->
            viewModelScope.launch {
                setState { copy(isLoading = false) }
                exception.printStackTrace()
            }
        }
        viewModelScope.launch(mainDispatcher + handler) {
            loginUseCase.login(LoginRequest(email, password)).onStart {
                setState { copy(isLoading = true) }
            }.onCompletion {
                setState { copy(isLoading = false) }
            }.catch {
                setState { copy(errorMessage = it.message) }
            }.collect { result ->
                if (result.data?.status == true) {
                    loginUseCase.setUser(result.data.data?.user as FirebaseUser)
                    setEffect { LoginContract.Effect.Navigation.ToLayout }
                }else{
                    setState { copy(errorMessage = result.data?.message) }
                }
            }
        }
    }
}