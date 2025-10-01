package com.faswet.sportify.ui.screens.profile

import com.faswet.sportify.R
import com.faswet.sportify.data.models.membershipplan.MemberShipPlan
import com.faswet.sportify.di.MainDispatcher
import com.faswet.sportify.domain.profile.IProfileUseCase
import com.faswet.sportify.ui.base.BaseViewModel
import com.faswet.sportify.ui.screens.profile.contract.ProfileContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCase: IProfileUseCase,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher,
) : BaseViewModel<ProfileContract.Event, ProfileContract.State, ProfileContract.Effect>() {
    init {
        setState { copy(userModel = profileUseCase.getUserData()) }
        setState { copy(avatar = getProfilePic(userModel.profilePicture?.profileId ?: 0)) }
        getMemberShip(viewState.value.userModel.membershipPlanId?:"")
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

    private fun getProfilePic(id :Int): Int{
        return when (id){
            1 -> R.drawable.ic_profile_1
            2 -> R.drawable.ic_profile_2
            3 -> R.drawable.ic_profile_3
            4 -> R.drawable.ic_profile_4
            5 -> R.drawable.ic_profile_5
            6 -> R.drawable.ic_profile_6
            7 -> R.drawable.ic_profile_7
            8 -> R.drawable.ic_profile_8
            9 -> R.drawable.ic_profile_9
            else -> R.drawable.ic_sportify_logo
        }
    }

    private fun getMemberShip(doc: String){
        val handler : CoroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
            viewModelScope.launch {
                setState { copy(isLoading = false) }
                exception.printStackTrace()
            }
        }
        viewModelScope.launch(mainDispatcher + handler) {
            profileUseCase.getMemberShip(doc).onStart {
                setState { copy(isLoading = true) }
            }.onCompletion {
                setState { copy(isLoading = false) }
            }.catch {
                setState { copy(errorMessage = it.message) }
            }.collect{status ->
                if (status.data?.status == true){
                    setState { copy(memberShipPlan = status.data.data?: MemberShipPlan()) }
                }else{
                    setState { copy(errorMessage = status.data?.message) }
                }
            }
        }
    }
}