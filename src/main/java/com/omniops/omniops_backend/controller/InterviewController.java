package com.omniops.omniops_backend.controller;

import com.omniops.omniops_backend.entity.Interview;
import com.omniops.omniops_backend.service.InterviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interviews")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class InterviewController {

    private final InterviewService interviewService;

    @PostMapping
    public ResponseEntity<Interview> saveInterview(
            @RequestBody Interview interview) {

        return ResponseEntity.ok(
                interviewService.save(interview)
        );
    }

    @GetMapping
    public ResponseEntity<List<Interview>> getAllInterviews() {

        return ResponseEntity.ok(
                interviewService.findAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Interview> getInterviewById(
            @PathVariable Long id) {

        Interview interview = interviewService.findById(id);

        if (interview == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(interview);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Interview> updateInterview(
            @PathVariable Long id,
            @RequestBody Interview interview) {

        return ResponseEntity.ok(
                interviewService.update(id, interview)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInterview(
            @PathVariable Long id) {

        interviewService.delete(id);

        return ResponseEntity.noContent().build();
    }

}