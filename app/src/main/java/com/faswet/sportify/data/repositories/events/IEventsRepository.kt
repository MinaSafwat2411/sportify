package com.faswet.sportify.data.repositories.events

import com.faswet.sportify.data.models.FirebaseResponse
import com.faswet.sportify.data.models.events.EventResponse
import com.faswet.sportify.data.repositories.base.IBaseRepository
import kotlinx.coroutines.flow.Flow

interface IEventsRepository : IBaseRepository {

    fun getAllEvents(): Flow<FirebaseResponse<List<EventResponse>>>

}