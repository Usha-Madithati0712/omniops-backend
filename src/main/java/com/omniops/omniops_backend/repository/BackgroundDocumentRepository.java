package com.omniops.omniops_backend.repository;

import com.omniops.omniops_backend.entity.BackgroundDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BackgroundDocumentRepository extends JpaRepository<BackgroundDocument, Long> {

    // Get all documents of a client
    List<BackgroundDocument> findByClientId(Integer clientId);

    // Check if same document already exists
    Optional<BackgroundDocument> findByClientIdAndDocumentType(
            Integer clientId,
            String documentType
    );
List<BackgroundDocument> findByBgvId(Long bgvId);

Optional<BackgroundDocument> findByBgvIdAndDocumentType(
        Long bgvId,
        String documentType
);
}
// =====================================================
// CEO Module
// =====================================================

