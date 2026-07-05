package com.omniops.omniops_backend.controller;

import com.omniops.omniops_backend.dto.BoxARequest;
import com.omniops.omniops_backend.entity.BoxA;
import com.omniops.omniops_backend.service.BoxAService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/boxa")
@RequiredArgsConstructor
@CrossOrigin("*")
public class BoxAController {

    private final BoxAService boxAService;

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

            String uploadDir = System.getProperty("java.io.tmpdir");

            String fileName =
                    UUID.randomUUID() + "_" + resume.getOriginalFilename();

            File destination = new File(uploadDir, fileName);

            resume.transferTo(destination);

            boxA.setResumeFile(fileName);
        }

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
}