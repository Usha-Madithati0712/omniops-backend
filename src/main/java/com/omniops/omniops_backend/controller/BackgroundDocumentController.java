package com.omniops.omniops_backend.controller;

import com.omniops.omniops_backend.entity.BackgroundDocument;
import com.omniops.omniops_backend.repository.BackgroundDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/background-documents")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BackgroundDocumentController {

    private final BackgroundDocumentRepository repository;

    @GetMapping("/bgv/{bgvId}")
    public List<BackgroundDocument> getDocumentsByBgvId(
            @PathVariable Long bgvId) {

        return repository.findByBgvId(bgvId);
    }

}