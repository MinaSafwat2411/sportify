package com.faswet.sportify.domain.events

import com.faswet.sportify.data.models.FirebaseResponse
import com.faswet.sportify.data.models.events.EventResponse
import com.faswet.sportify.domain.base.IBaseUseCase
import kotlinx.coroutines.flow.Flow

interface IEventsUseCase : IBaseUseCase {
    fun getAllEvents(): Flow<FirebaseResponse<List<EventResponse>>>

}