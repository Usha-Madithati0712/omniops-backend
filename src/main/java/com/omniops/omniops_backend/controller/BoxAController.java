package com.omniops.omniops_backend.controller;

import com.omniops.omniops_backend.dto.BoxARequest;
import com.omniops.omniops_backend.entity.BoxA;
import com.omniops.omniops_backend.service.BoxAService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.omniops.omniops_backend.service.storage.FileStorageService;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.MalformedURLException;
@RestController
@RequestMapping("/api/boxa")
@RequiredArgsConstructor
@CrossOrigin("*")
public class BoxAController {

    private final BoxAService boxAService;
private final FileStorageService fileStorageService;
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BoxA save(@ModelAttribute BoxARequest request) throws IOException {
System.out.println("========== BOX A CONTROLLER HIT ==========");
        BoxA boxA = new BoxA();

        boxA.setFullName(request.getFullName());
        boxA.setPhone(request.getPhone());
        boxA.setEmail(request.getEmail());
        boxA.setLocation(request.getLocation());
        boxA.setCompany(request.getCompany());
        boxA.setExperience(request.getExperience());
        boxA.setPreviousCompany(request.getPreviousCompany());
        boxA.setYearsWorked(request.getYearsWorked());
        boxA.setDesignation(request.getDesignation());
        boxA.setQualification(request.getQualification());
        boxA.setCollege(request.getCollege());
        boxA.setGraduationYear(request.getGraduationYear());
        boxA.setTargetRole(request.getTargetRole());
        boxA.setTechnology(request.getTechnology());
        boxA.setExpectedCTC(request.getExpectedCTC());
        boxA.setPreferredLocation(request.getPreferredLocation());
        boxA.setUsername(request.getUsername());
        boxA.setPassword(request.getPassword());
        boxA.setAssignedRecruiter(request.getAssignedRecruiter());

        MultipartFile resume = request.getResume();

        if (resume != null && !resume.isEmpty()) {

        if (resume != null && !resume.isEmpty()) {

    String fileName = fileStorageService.saveFile(
            resume,
            "resumes"
    );

    boxA.setResumeFile(fileName);

}
}

System.out.println("Saved Resume : " + boxA.getResumeFile());

return boxAService.save(boxA);
    }

    @GetMapping
    public List<BoxA> all() {
        return boxAService.findAll();
    }

    @GetMapping("/{id}")
    public BoxA get(@PathVariable Integer id) {
        return boxAService.findById(id);
    }

    @PutMapping("/{id}")
    public BoxA update(@PathVariable Integer id,
                       @RequestBody BoxA boxA) {
        return boxAService.update(id, boxA);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        boxAService.delete(id);
    }
    @GetMapping("/resume/{id}")
public ResponseEntity<Resource> viewResume(
        @PathVariable Integer id) throws MalformedURLException {

    BoxA boxA = boxAService.findById(id);

    if (boxA == null || boxA.getResumeFile() == null) {
        return ResponseEntity.notFound().build();
    }

    Path path = Paths.get("uploads")
            .resolve(boxA.getResumeFile());

    Resource resource = new UrlResource(path.toUri());

    if (!resource.exists() || !resource.isReadable()) {
        return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok()
            .header(
                    HttpHeaders.CONTENT_DISPOSITION,
                    "inline; filename=\"" +
                            boxA.getResumeFile() +
                            "\""
            )
            .body(resource);
}
}