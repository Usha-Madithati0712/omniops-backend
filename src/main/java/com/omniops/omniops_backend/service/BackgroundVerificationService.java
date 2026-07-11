package com.omniops.omniops_backend.service;

import com.omniops.omniops_backend.entity.BackgroundVerification;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BackgroundVerificationService {

   BackgroundVerification submit(

        Integer clientId,

        String fullName,

        String phoneNumber,

        String uploadedBy,

        MultipartFile resumeFile,

        MultipartFile optFile,

        MultipartFile licenseFile,

        MultipartFile aadhaarFile,

        MultipartFile ssnFile,

        MultipartFile stateIdFile,

        MultipartFile passportFile,

        MultipartFile degreeFile,

        MultipartFile transcriptFile,

        MultipartFile chequeFile,

        MultipartFile zipFile

) throws IOException;

    List<BackgroundVerification> getByClient(Integer clientId);

}