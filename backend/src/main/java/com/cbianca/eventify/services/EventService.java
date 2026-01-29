package com.cbianca.eventify.services;

import com.cbianca.eventify.entities.events.CreateEventRequest;
import com.cbianca.eventify.entities.events.Event;

import java.util.UUID;

public interface EventService {


    Event createEvent(UUID organizerId, CreateEventRequest event);
}
