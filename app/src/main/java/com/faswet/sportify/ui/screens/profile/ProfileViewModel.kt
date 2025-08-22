package com.faswet.sportify.ui.screens.profile

import com.faswet.sportify.di.MainDispatcher
import com.faswet.sportify.domain.profile.IProfileUseCase
import com.faswet.sportify.ui.base.BaseViewModel
import com.faswet.sportify.ui.screens.profile.contract.ProfileContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCase: IProfileUseCase,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher,
) : BaseViewModel<ProfileContract.Event, ProfileContract.State, ProfileContract.Effect>() {
    init {
        setState { copy(userModel = profileUseCase.getUserData()) }
    }
    override fun handleEvents(event: ProfileContract.Event) {
        when (event) {
            ProfileContract.Event.OnBackClicked -> {
                setEffect { ProfileContract.Effect.Navigation.Back }
            }
        }
    }

    override fun setInitialState() = ProfileContract.State(
        isLoading = false
    )
}