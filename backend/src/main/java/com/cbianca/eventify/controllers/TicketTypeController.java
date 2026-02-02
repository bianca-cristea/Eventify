package com.cbianca.eventify.controllers;

import com.cbianca.eventify.services.TicketTypeService;
import com.sun.net.httpserver.HttpsServer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.cbianca.eventify.utils.JwtUtil.parseUserId;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/events/{eventId}/ticket-types")
public class TicketTypeController {
    private final TicketTypeService ticketTypeService;

    @PostMapping(path = "/{ticketTypeId}/tickets")
    public ResponseEntity<Void> purchaseTicket(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable UUID ticketTypeId
    ){
        ticketTypeService.purchaseTicket(parseUserId(jwt),ticketTypeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
