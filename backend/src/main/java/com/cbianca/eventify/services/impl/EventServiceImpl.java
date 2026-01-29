package com.cbianca.eventify.services.impl;

import com.cbianca.eventify.entities.events.CreateEventRequest;
import com.cbianca.eventify.entities.events.Event;
import com.cbianca.eventify.entities.ticket_types.TicketType;
import com.cbianca.eventify.entities.user.User;
import com.cbianca.eventify.exceptions.UserNotFoundException;
import com.cbianca.eventify.repositories.EventRepository;
import com.cbianca.eventify.repositories.UserRepository;
import com.cbianca.eventify.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Override
    public Event createEvent(UUID organizerId, CreateEventRequest event) {
        User organizer = userRepository.findById(organizerId).orElseThrow(() -> new UserNotFoundException(String.format("User with ID '%s' not found", organizerId)));

        List<TicketType> ticketTypesToCreate = event.getTicketTypes().stream().map(ticketType -> {
            TicketType ticketTypeToCreate = new TicketType();
            ticketTypeToCreate.setName(ticketType.getName());
            ticketTypeToCreate.setPrice(ticketType.getPrice());
            ticketTypeToCreate.setDescription(ticketType.getDescription());
            ticketTypeToCreate.setTotalAvailable(ticketType.getTotalAvailable());
            return ticketTypeToCreate;
        }).toList();

        Event eventToCreate  = new Event();
        eventToCreate.setName(event.getName());
        eventToCreate.setStart(event.getStart());
        eventToCreate.setEnd(event.getEnd());
        eventToCreate.setVenue(event.getVenue());
        eventToCreate.setSalesStartDate(event.getSalesStart());
        eventToCreate.setSalesEndDate(event.getSalesEnd());
        eventToCreate.setStatus(event.getStatus());
        eventToCreate.setOrganizer(organizer);
        eventToCreate.setTicketTypes(ticketTypesToCreate);

        return eventRepository.save(eventToCreate);
    }

}
