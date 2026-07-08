package com.omniops.omniops_backend.controller;

import com.omniops.omniops_backend.entity.TrainerAssignment;
import com.omniops.omniops_backend.service.TrainerAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainer-assignments")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TrainerAssignmentController {

    private final TrainerAssignmentService trainerAssignmentService;

    @PostMapping
    public ResponseEntity<TrainerAssignment> saveTrainerAssignment(
            @RequestBody TrainerAssignment trainerAssignment) {

        return ResponseEntity.ok(
                trainerAssignmentService.save(trainerAssignment)
        );
    }

    @GetMapping
    public ResponseEntity<List<TrainerAssignment>> getAllTrainerAssignments() {

        return ResponseEntity.ok(
                trainerAssignmentService.findAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainerAssignment> getTrainerAssignmentById(
            @PathVariable Long id) {

        TrainerAssignment trainerAssignment =
                trainerAssignmentService.findById(id);

        if (trainerAssignment == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(trainerAssignment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainerAssignment> updateTrainerAssignment(
            @PathVariable Long id,
            @RequestBody TrainerAssignment trainerAssignment) {

        return ResponseEntity.ok(
                trainerAssignmentService.update(id, trainerAssignment)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainerAssignment(
            @PathVariable Long id) {

        trainerAssignmentService.delete(id);

        return ResponseEntity.noContent().build();
    }

}