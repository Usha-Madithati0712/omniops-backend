package com.omniops.omniops_backend.service.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import java.net.MalformedURLException;
@Service("storageFileStorageService")
public class FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String saveFile(MultipartFile file, String folder) throws IOException {

        if (file == null || file.isEmpty()) {
            return null;
        }

        Path uploadPath = Paths.get(uploadDir, folder);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String originalFileName =
                StringUtils.cleanPath(file.getOriginalFilename());

        String extension = "";

        int index = originalFileName.lastIndexOf(".");

        if (index != -1) {
            extension = originalFileName.substring(index);
        }

        String fileName =
                UUID.randomUUID().toString() + extension;

        Path destination =
                uploadPath.resolve(fileName);

        Files.copy(
                file.getInputStream(),
                destination,
                StandardCopyOption.REPLACE_EXISTING
        );

       return fileName;
    }
public Resource loadFile(String folder, String fileName) throws MalformedURLException {

    Path filePath = Paths.get(uploadDir, folder).resolve(fileName).normalize();

    Resource resource = new UrlResource(filePath.toUri());

    if(resource.exists() && resource.isReadable()){
        return resource;
    }

    throw new RuntimeException("File not found : " + fileName);

}
}