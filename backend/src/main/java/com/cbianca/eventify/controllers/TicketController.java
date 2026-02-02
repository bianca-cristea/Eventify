package com.cbianca.eventify.controllers;

import com.cbianca.eventify.dtos.GetTicketResponseDTO;
import com.cbianca.eventify.dtos.ListTicketResponseDTO;
import com.cbianca.eventify.mapper.TicketMapper;
import com.cbianca.eventify.services.QrCodeService;
import com.cbianca.eventify.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.cbianca.eventify.utils.JwtUtil.parseUserId;

@RestController
@RequestMapping(path = "/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;
    private final TicketMapper ticketMapper;
    private final QrCodeService qrCodeService;



    @GetMapping
    public Page<ListTicketResponseDTO> listTickets(
            @AuthenticationPrincipal Jwt jwt,
            Pageable pageable
    ){
       return ticketService.listTicketsForUser(
                parseUserId(jwt),
                pageable
        ).map(ticketMapper::toListTicketResponseDTO);

    }

    @GetMapping(path = "/{tickteId}")
    public ResponseEntity<GetTicketResponseDTO> getTicket(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable UUID ticketId
    ){
        return ticketService.getTicketForUser(parseUserId(jwt),ticketId)
                .map(ticketMapper::toGetTicketResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/{ticketId}/qr-codes")
    public ResponseEntity<byte[]> getTicketQrCode(
        @AuthenticationPrincipal Jwt jwt,
        @PathVariable UUID ticketID
    ){
        byte[] qrCodeImage = qrCodeService.getQrCodeImageForUserAndTicket(parseUserId(jwt),ticketID)
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(qrCodeImage.length);

        return  ResponseEntity.ok().headers(headers).body(qrCodeImage);


    }

}
