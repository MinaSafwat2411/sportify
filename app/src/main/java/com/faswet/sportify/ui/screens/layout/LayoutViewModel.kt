package com.faswet.sportify.ui.screens.layout

import com.faswet.sportify.R
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
        setState { copy(avatar = getProfilePic(userModel?.profilePicture?.profileId ?: 0)) }
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
}