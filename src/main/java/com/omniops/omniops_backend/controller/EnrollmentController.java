package com.omniops.omniops_backend.controller;

import com.omniops.omniops_backend.entity.Enrollment;
import com.omniops.omniops_backend.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    // CREATE
    @PostMapping
    public ResponseEntity<Enrollment> createEnrollment(
            @RequestBody Enrollment enrollment) {

        return ResponseEntity.ok(
                enrollmentService.save(enrollment)
        );
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Enrollment>> getAllEnrollments() {

        return ResponseEntity.ok(
                enrollmentService.findAll()
        );
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getEnrollmentById(
            @PathVariable Long id) {

        Enrollment enrollment = enrollmentService.findById(id);

        if (enrollment == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(enrollment);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Enrollment> updateEnrollment(
            @PathVariable Long id,
            @RequestBody Enrollment enrollment) {

        Enrollment updated =
                enrollmentService.update(id, enrollment);

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEnrollment(
            @PathVariable Long id) {

        enrollmentService.delete(id);

        return ResponseEntity.ok("Enrollment Deleted Successfully");
    }

}