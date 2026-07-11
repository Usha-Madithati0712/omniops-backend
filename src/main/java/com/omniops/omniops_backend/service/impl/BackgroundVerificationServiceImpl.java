package com.omniops.omniops_backend.service.impl;
import com.omniops.omniops_backend.entity.BackgroundDocument;
import com.omniops.omniops_backend.repository.BackgroundDocumentRepository;
import com.omniops.omniops_backend.service.storage.FileStorageService;
import com.omniops.omniops_backend.entity.BackgroundVerification;
import com.omniops.omniops_backend.entity.Client;
import com.omniops.omniops_backend.repository.BackgroundVerificationRepository;
import com.omniops.omniops_backend.repository.ClientRepository;
import com.omniops.omniops_backend.service.BackgroundVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BackgroundVerificationServiceImpl
        implements BackgroundVerificationService {

    private final BackgroundVerificationRepository repository;
    private final ClientRepository clientRepository;
    private final BackgroundDocumentRepository backgroundDocumentRepository;
private final FileStorageService fileStorageService;

   @Override
public BackgroundVerification submit(

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

) throws IOException {

       Client client = clientRepository.findById(clientId)
        .orElseThrow(() -> new RuntimeException("Client not found"));

BackgroundVerification bgv = new BackgroundVerification();

bgv.setClient(client);

bgv.setFullName(fullName);

bgv.setPhoneNumber(phoneNumber);

bgv.setSubmittedBy(uploadedBy);

bgv.setStatus("Completed");

bgv.setSubmittedAt(LocalDateTime.now());

BackgroundVerification savedBGV = repository.save(bgv);
System.out.println("=================================");
System.out.println("Saved BGV ID = " + savedBGV.getBgvId());
System.out.println("=================================");
saveDocument(savedBGV, client, resumeFile, "Resume");
saveDocument(savedBGV, client, optFile, "OPT");
saveDocument(savedBGV, client, licenseFile, "Driving License");
saveDocument(savedBGV, client, aadhaarFile, "Aadhaar");
saveDocument(savedBGV, client, ssnFile, "SSN");
saveDocument(savedBGV, client, stateIdFile, "State ID");
saveDocument(savedBGV, client, passportFile, "Passport");
saveDocument(savedBGV, client, degreeFile, "Degree");
saveDocument(savedBGV, client, transcriptFile, "Transcript");
saveDocument(savedBGV, client, chequeFile, "Void Cheque");
saveDocument(savedBGV, client, zipFile, "ZIP");

return savedBGV;
    }

    @Override
    public List<BackgroundVerification> getByClient(Integer clientId) {

        return repository.findByClientClientId(clientId);

    }
private void saveDocument(
        BackgroundVerification bgv,
        Client client,
        MultipartFile file,
        String documentType
)throws IOException {

    if (file == null || file.isEmpty()) {
        return;
    }

  String storedFileName =
        fileStorageService.saveFile(file, "background-verification");

    BackgroundDocument document = new BackgroundDocument();
document.setBgvId(bgv.getBgvId());
    document.setClientId(client.getClientId());
document.setClientName(client.getCompanyName());

    document.setDocumentType(documentType);

    document.setOriginalFileName(file.getOriginalFilename());

  document.setStoredFileName(storedFileName);

   document.setFileUrl(
        "/uploads/background-verification/" + storedFileName
);

    document.setUploadedBy("Recruiter");

    document.setUploadedAt(LocalDateTime.now());

    backgroundDocumentRepository.save(document);

}
}