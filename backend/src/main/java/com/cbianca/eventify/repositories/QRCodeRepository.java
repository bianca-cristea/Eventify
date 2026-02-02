package com.cbianca.eventify.repositories;

import com.cbianca.eventify.entities.qr.QrCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;


@Repository
public interface QRCodeRepository extends JpaRepository<QrCode, UUID> {
}
