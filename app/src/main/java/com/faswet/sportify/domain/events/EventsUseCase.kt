package com.faswet.sportify.domain.events

import com.faswet.sportify.data.models.FirebaseResponse
import com.faswet.sportify.data.models.events.EventResponse
import com.faswet.sportify.data.repositories.events.IEventsRepository
import com.faswet.sportify.domain.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EventsUseCase @Inject constructor(
    private val mEventsRepository: IEventsRepository,
) : BaseUseCase(mEventsRepository), IEventsUseCase {
    override fun getAllEvents(): Flow<FirebaseResponse<List<EventResponse>>> {
        return mEventsRepository.getAllEvents()
    }
}