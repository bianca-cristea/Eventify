package com.cbianca.eventify.controllers;

import com.cbianca.eventify.dtos.CreateEventRequestDTO;
import com.cbianca.eventify.dtos.CreateEventResponseDTO;
import com.cbianca.eventify.entities.events.CreateEventRequest;
import com.cbianca.eventify.entities.events.Event;
import com.cbianca.eventify.mapper.EventMapper;
import com.cbianca.eventify.services.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventMapper eventMapper;
    private final EventService eventService;

    @PostMapping
    public ResponseEntity<CreateEventResponseDTO> createEvent(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody CreateEventRequestDTO createEventRequestDTO) {

        CreateEventRequest createEventRequest =
                eventMapper.fromDTO(createEventRequestDTO);

        UUID userId = UUID.fromString(jwt.getSubject());

        Event eventCreated =
                eventService.createEvent(userId, createEventRequest);

        CreateEventResponseDTO responseDTO =
                eventMapper.toDTO(eventCreated);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
