package com.omniops.omniops_backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String storeFile(MultipartFile file) throws IOException {

        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String originalFileName =
                StringUtils.cleanPath(file.getOriginalFilename());

        String extension = "";

        int index = originalFileName.lastIndexOf(".");

        if (index > 0) {
            extension = originalFileName.substring(index);
        }

        String storedFileName =
                UUID.randomUUID().toString() + extension;

        Path targetLocation =
                uploadPath.resolve(storedFileName);

        Files.copy(
                file.getInputStream(),
                targetLocation,
                StandardCopyOption.REPLACE_EXISTING
        );

        return storedFileName;
    }

    public Path getFilePath(String storedFileName) {

        return Paths.get(uploadDir).resolve(storedFileName);

    }

}