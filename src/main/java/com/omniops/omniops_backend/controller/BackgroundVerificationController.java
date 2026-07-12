package com.omniops.omniops_backend.controller;

import com.omniops.omniops_backend.repository.BackgroundVerificationRepository;
import com.omniops.omniops_backend.entity.BackgroundVerification;

import com.omniops.omniops_backend.service.BackgroundVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/bgv")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BackgroundVerificationController {

    private final BackgroundVerificationService backgroundVerificationService;
    private final BackgroundVerificationRepository backgroundVerificationRepository;

    @PostMapping("/submit")
    public ResponseEntity<?> submit(

            @RequestParam Integer clientId,

            @RequestParam String fullName,

            @RequestParam String phoneNumber,

            @RequestParam String uploadedBy,

            @RequestParam(required = false) MultipartFile resumeFile,

            @RequestParam(required = false) MultipartFile optFile,

            @RequestParam(required = false) MultipartFile licenseFile,

            @RequestParam(required = false) MultipartFile aadhaarFile,

            @RequestParam(required = false) MultipartFile ssnFile,

            @RequestParam(required = false) MultipartFile stateIdFile,

            @RequestParam(required = false) MultipartFile passportFile,

            @RequestParam(required = false) MultipartFile degreeFile,

            @RequestParam(required = false) MultipartFile transcriptFile,

            @RequestParam(required = false) MultipartFile chequeFile,

            @RequestParam(required = false) MultipartFile zipFile

    ) throws IOException {
System.out.println("############################");
System.out.println("CONTROLLER HIT");
System.out.println("resumeFile = " + resumeFile);
System.out.println("############################");
        BackgroundVerification bgv = backgroundVerificationService.submit(
                clientId,
                fullName,
                phoneNumber,
                uploadedBy,
                resumeFile,
                optFile,
                licenseFile,
                aadhaarFile,
                ssnFile,
                stateIdFile,
                passportFile,
                degreeFile,
                transcriptFile,
                chequeFile,
                zipFile);

        return ResponseEntity.ok(bgv);

    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<BackgroundVerification>> getClientBgv(
            @PathVariable Integer clientId) {

        return ResponseEntity.ok(
                backgroundVerificationService.getByClient(clientId));

    }

   @GetMapping("/all")
public ResponseEntity<?> getAllBackgroundVerifications() {

    try {

        return ResponseEntity.ok(backgroundVerificationRepository.findAll());

    } catch (Exception e) {

        e.printStackTrace();

        return ResponseEntity.internalServerError()
                .body(e.getMessage());

    }

}
}