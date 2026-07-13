package com.omniops.omniops_backend.controller;

import com.omniops.omniops_backend.entity.BackgroundDocument;
import com.omniops.omniops_backend.repository.BackgroundDocumentRepository;
import com.omniops.omniops_backend.service.storage.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/background-documents")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BackgroundDocumentController {

    private final BackgroundDocumentRepository repository;
    private final FileStorageService fileStorageService;

    @GetMapping("/bgv/{bgvId}")
    public List<BackgroundDocument> getDocumentsByBgvId(
            @PathVariable Long bgvId) {

        return repository.findByBgvId(bgvId);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<Resource> viewDocument(
            @PathVariable Long id) throws IOException {

        BackgroundDocument document = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document Not Found"));

        Resource resource = fileStorageService.loadFile(
                "background-verification",
                document.getStoredFileName()
        );

        String contentType = MediaTypeFactory
                .getMediaType(resource)
                .orElse(MediaType.APPLICATION_OCTET_STREAM)
                .toString();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .body(resource);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadDocument(
            @PathVariable Long id) throws IOException {

        BackgroundDocument document = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document Not Found"));

        Resource resource = fileStorageService.loadFile(
                "background-verification",
                document.getStoredFileName()
        );

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" +
                                document.getOriginalFileName() +
                                "\""
                )
                .body(resource);
    }
}