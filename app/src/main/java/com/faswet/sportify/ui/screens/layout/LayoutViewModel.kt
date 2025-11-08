package com.faswet.sportify.ui.screens.layout

import com.faswet.sportify.R
import com.faswet.sportify.di.MainDispatcher
import com.faswet.sportify.domain.booking.IBookingUseCase
import com.faswet.sportify.domain.events.IEventsUseCase
import com.faswet.sportify.domain.layout.ILayoutUseCase
import com.faswet.sportify.ui.base.BaseViewModel
import com.faswet.sportify.ui.screens.layout.contract.LayoutContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class LayoutViewModel @Inject constructor(
    private val layoutUseCase: ILayoutUseCase,
    private val eventsUseCase: IEventsUseCase,
    private val bookingUseCase: IBookingUseCase,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher,
) : BaseViewModel<LayoutContract.Event, LayoutContract.State, LayoutContract.Effect>() {

    private var dataJob: Job? = null

    init {
        setState { copy(userModel = layoutUseCase.getUserData()) }
        setState { copy(avatar = getProfilePic(userModel?.profilePicture?.profileId ?: 0)) }
        handleEvents(LayoutContract.Event.GetData)
    }

    override fun handleEvents(event: LayoutContract.Event) {
        when (event) {
            is LayoutContract.Event.OnScreenChanged -> {
                setState { copy(currentScreen = event.screen) }
            }

            is LayoutContract.Event.OnBottomNavClicked -> {
                setState { copy(currentScreen = event.screen) }
            }

            is LayoutContract.Event.OnSettingsClicked -> {
                setEffect { LayoutContract.Effect.Navigation.ToSettings }
            }

            is LayoutContract.Event.OnProfileClicked -> {
                setEffect { LayoutContract.Effect.Navigation.ToProfile }
            }

            LayoutContract.Event.GetData -> getData()

            LayoutContract.Event.OnBackClicked -> onBackClicked()
            is LayoutContract.Event.OnDateChanged -> {
                setState { copy(selectedDate = event.date) }
            }
            is LayoutContract.Event.OnNextMonthClicked-> {
                setState { copy(selectedDate = addMonthsToDate(selectedDate, 1))  }
            }
            LayoutContract.Event.OnPreviousMonthClicked -> {
                setState { copy(selectedDate = addMonthsToDate(selectedDate, -1))  }
            }
        }
    }

    override fun setInitialState() = LayoutContract.State(
        currentScreen = 0,
        userModel = null
    )

    private fun getProfilePic(id: Int): Int {
        return when (id) {
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

    private fun getData() {
        val handler = CoroutineExceptionHandler { _, exception ->
            viewModelScope.launch {
                setState { copy(isLoading = false) }
                exception.printStackTrace()
            }
        }
        dataJob?.cancel()

        dataJob = viewModelScope.launch(handler + mainDispatcher) {
            combine(
                eventsUseCase.getAllEvents(),
                bookingUseCase.getAllBooking(),
            ) { eventsResponse, bookingsResponse ->
                LayoutContract.State(
                    events = eventsResponse.data.orEmpty(),
                    bookings = bookingsResponse.data.orEmpty(),
                    isLoading = false
                )
            }.collect { newState ->
                setState { copy(events = newState.events, bookings = newState.bookings) }
            }
        }
    }

    private fun  onBackClicked(){
        if (viewState.value.currentScreen != 0) {
            handleEvents(LayoutContract.Event.OnScreenChanged(0))
        }else{
            setEffect { LayoutContract.Effect.Navigation.Back }
        }
    }

    fun addMonthsToDate(date: Date, months: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(Calendar.MONTH, months)
        return calendar.time
    }

    override fun onCleared() {
        dataJob?.cancel()
        super.onCleared()
    }
}