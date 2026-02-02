package com.cbianca.eventify.repositories;

import com.cbianca.eventify.entities.qr.QrCode;
import com.cbianca.eventify.entities.qr.QrCodeStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface QRCodeRepository extends JpaRepository<QrCode, UUID> {
    Optional<QrCode> findByTicketIdAndTicketPurchaseId(UUID ticketId, UUID ticketPurchaseId);
    Optional<QrCode> findByIdAndStatus(UUID id, QrCodeStatusEnum status);
}
