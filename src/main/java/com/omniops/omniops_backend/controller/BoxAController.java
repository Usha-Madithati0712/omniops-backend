package com.omniops.omniops_backend.controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import com.omniops.omniops_backend.entity.BoxA;
import com.omniops.omniops_backend.service.BoxAService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boxa")
@RequiredArgsConstructor
@CrossOrigin("*")
public class BoxAController {

    private final BoxAService boxAService;

    @PostMapping(consumes = {"multipart/form-data"})
public BoxA save(

     @RequestParam("fullName") String fullName,

@RequestParam("phone") String phone,

@RequestParam("email") String email,

@RequestParam(value = "location", required = false) String location,

@RequestParam(value = "company", required = false) String company,

@RequestParam(value = "experience", required = false) String experience,

@RequestParam(value = "resume", required = false) MultipartFile resume,

@RequestParam(value = "previousCompany", required = false) String previousCompany,

@RequestParam(value = "yearsWorked", required = false) String yearsWorked,

@RequestParam(value = "designation", required = false) String designation,

@RequestParam(value = "qualification", required = false) String qualification,

@RequestParam(value = "college", required = false) String college,

@RequestParam(value = "graduationYear", required = false) String graduationYear,

@RequestParam(value = "targetRole", required = false) String targetRole,

@RequestParam(value = "technology", required = false) String technology,

@RequestParam(value = "expectedCTC", required = false) String expectedCTC,

@RequestParam(value = "preferredLocation", required = false) String preferredLocation,

@RequestParam(value = "username", required = false) String username,

@RequestParam(value = "password", required = false) String password,

@RequestParam(value = "assignedRecruiter", required = false) String assignedRecruiter

) throws IOException {
    BoxA boxA = new BoxA();

    boxA.setFullName(fullName);
    boxA.setPhone(phone);
    boxA.setEmail(email);
    boxA.setLocation(location);
    boxA.setCompany(company);
    boxA.setExperience(experience);
    boxA.setPreviousCompany(previousCompany);
    boxA.setYearsWorked(yearsWorked);
    boxA.setDesignation(designation);
    boxA.setQualification(qualification);
    boxA.setCollege(college);
    boxA.setGraduationYear(graduationYear);
    boxA.setTargetRole(targetRole);
    boxA.setTechnology(technology);
    boxA.setExpectedCTC(expectedCTC);
    boxA.setPreferredLocation(preferredLocation);
    boxA.setUsername(username);
    boxA.setPassword(password);
    boxA.setAssignedRecruiter(assignedRecruiter);

   try {

    if (resume != null && !resume.isEmpty()) {

        String uploadDir = "uploads/";

        File dir = new File(uploadDir);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        String fileName =
                UUID.randomUUID() + "_" + resume.getOriginalFilename();

        resume.transferTo(new File(uploadDir + fileName));

        boxA.setResumeFile(fileName);
    }

} catch (Exception e) {

    e.printStackTrace();

    throw e;
    

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