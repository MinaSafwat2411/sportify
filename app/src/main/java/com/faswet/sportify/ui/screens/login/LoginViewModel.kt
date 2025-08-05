package com.faswet.sportify.ui.screens.login

import android.util.Patterns
import com.faswet.sportify.R
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
            is LoginContract.Event.OnEmailChanged -> {
                setState { copy(email = event.email, emailError = null, passwordError = null) }
            }
            is LoginContract.Event.OnPasswordChanged -> {
                setState { copy(password = event.password, emailError = null, passwordError = null) }
            }
            is LoginContract.Event.OnLoginClicked -> {
                onLogin(viewState.value.email, viewState.value.password)
            }
            is LoginContract.Event.OnSignupClicked -> {
            }
            is LoginContract.Event.OnForgotPasswordClicked -> {
            }
            is LoginContract.Event.OnGoogleLoginClicked -> {
            }
            is LoginContract.Event.OnFacebookLoginClicked -> {
            }
            LoginContract.Event.OnShowHidePasswordClicked -> {
                setState { copy(isPasswordVisible = !viewState.value.isPasswordVisible) }
            }
        }
    }

    override fun setInitialState() = LoginContract.State()

    private fun onLogin(email: String, password: String){
        if (viewState.value.email.isEmpty()) {
            setState { copy(emailError = R.string.email_is_required) }
            if (viewState.value.password.isEmpty()) {
                setState { copy(passwordError = R.string.password_is_required) }
                return
            }
            return
        }
        if (viewState.value.password.isEmpty()) {
            setState { copy(passwordError = R.string.password_is_required) }
            if (!isValidEmail(email)) {
                setState { copy(emailError = R.string.invalid_email) }
                return
            }
            return
        }
        if (!isValidEmail(email)) {
            setState { copy(emailError = R.string.invalid_email) }
            return
        }
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
                    loginUseCase.setUserUID(result.data.data?.user as FirebaseUser)
                    setEffect { LoginContract.Effect.Navigation.ToLayout }
                }else{
                    setState { copy(errorMessage = result.data?.message) }
                }
            }
        }
    }
    fun isValidEmail(email: String): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}