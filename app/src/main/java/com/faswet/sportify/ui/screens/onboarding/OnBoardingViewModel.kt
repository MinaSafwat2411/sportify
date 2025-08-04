package com.faswet.sportify.ui.screens.onboarding

import com.faswet.sportify.R
import com.faswet.sportify.domain.onboarding.OnBoardingUseCase
import com.faswet.sportify.ui.base.BaseViewModel
import com.faswet.sportify.ui.screens.onboarding.contract.OnBoardingContract
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val onBoardingUseCase: OnBoardingUseCase
) : BaseViewModel<OnBoardingContract.Event, OnBoardingContract.State,OnBoardingContract.Effect>() {
    init {
        getLists()
    }
    override fun handleEvents(event: OnBoardingContract.Event) {
        when (event) {
            OnBoardingContract.Event.OnNextClicked -> onNextClicked()
            is OnBoardingContract.Event.OnScreenChanged -> onScreenChanged(event.screen)
            OnBoardingContract.Event.OnSkipClicked -> onSkipClicked()
        }
    }

    override fun setInitialState() = OnBoardingContract.State(
        currentScreen = 0,
        isLastScreen = false
    )

    private fun getLists(){
        setState {
            copy(
                titleList = listOf(
                    R.string.onboarding_title_1,
                    R.string.onboarding_title_2,
                    R.string.onboarding_title_3,
                    R.string.onboarding_title_4,
                    R.string.onboarding_title_5
                ),
                descriptionList = listOf(
                    R.string.onboarding_description_1,
                    R.string.onboarding_description_2,
                    R.string.onboarding_description_3,
                    R.string.onboarding_description_4,
                    R.string.onboarding_description_5
                ),
                imageList = listOf(
                    R.drawable.ic_onboarding_1,
                    R.drawable.ic_onboarding_2,
                    R.drawable.ic_onboarding_3,
                    R.drawable.ic_onboarding_4,
                    R.drawable.ic_onboarding_5,
                )
            )
        }
    }
    private fun  onNextClicked(){
        if (viewState.value.currentScreen==4){
            onBoardingUseCase.setAppIsOpened()
            setEffect { OnBoardingContract.Effect.Navigation.ToLogin }
            return
        }
        setState {
            copy(
                currentScreen = currentScreen + 1
            )
        }
    }

    private fun onSkipClicked(){
        onBoardingUseCase.setAppIsOpened()
        setEffect { OnBoardingContract.Effect.Navigation.ToLogin }
    }

    private fun onScreenChanged(screen: Int) {
        setState {
            copy(
                currentScreen = screen
            )
        }
    }
}