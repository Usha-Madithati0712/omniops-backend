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

       System.out.println("=================================");
System.out.println("CLIENT ID RECEIVED FROM FRONTEND = " + clientId);
System.out.println("=================================");

Client client = clientRepository.findById(clientId)
        .orElseThrow(() -> new RuntimeException("Client not found"));
       System.out.println("=================================");
System.out.println("CLIENT LOADED");
System.out.println("ID   = " + client.getClientId());
System.out.println("NAME = " + client.getCompanyName());
System.out.println("=================================");

BackgroundVerification bgv = new BackgroundVerification();

bgv.setClient(client);
System.out.println("=================================");
System.out.println("CLIENT SAVED INTO BGV");
System.out.println("ID   = " + bgv.getClient().getClientId());
System.out.println("NAME = " + bgv.getClient().getCompanyName());
System.out.println("=================================");
bgv.setFullName(fullName);

bgv.setPhoneNumber(phoneNumber);

bgv.setSubmittedBy(uploadedBy);

bgv.setStatus("Completed");

bgv.setSubmittedAt(LocalDateTime.now());

BackgroundVerification savedBGV = repository.save(bgv);

System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
System.out.println("AFTER BGV SAVE");
System.out.println("BGV ID = " + savedBGV.getBgvId());
System.out.println("Resume Object = " + resumeFile);
System.out.println("CALLING saveDocument()");
System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");

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

   if (file == null) {
    System.out.println("================================");
    System.out.println("FILE IS NULL : " + documentType);
    System.out.println("================================");
    return;
}

if (file.isEmpty()) {
    System.out.println("================================");
    System.out.println("FILE IS EMPTY : " + documentType);
    System.out.println("================================");
    return;
}

System.out.println("================================");
System.out.println("FILE RECEIVED : " + documentType);
System.out.println("NAME : " + file.getOriginalFilename());
System.out.println("SIZE : " + file.getSize());
System.out.println("================================");

  String storedFileName =
        fileStorageService.saveFile(file, "background-verification");
System.out.println("FILE STORED");
System.out.println(storedFileName);
    BackgroundDocument document = new BackgroundDocument();
document.setBgvId(bgv.getBgvId());
System.out.println("BGV ID from entity = " + bgv.getBgvId());
System.out.println("BGV ID inside document = " + document.getBgvId());
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
System.out.println("===============");
System.out.println("BGV in bgv object      = " + bgv.getBgvId());
System.out.println("BGV in document object = " + document.getBgvId());
System.out.println("Client ID             = " + document.getClientId());
System.out.println("Document Type         = " + document.getDocumentType());
System.out.println("===============");
System.out.println("ABOUT TO SAVE DOCUMENT");
backgroundDocumentRepository.save(document);

System.out.println("DOCUMENT SAVED SUCCESSFULLY");




}
}