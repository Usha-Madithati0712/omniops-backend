package com.omniops.omniops_backend.controller;

import com.omniops.omniops_backend.entity.InternalHiring;
import com.omniops.omniops_backend.service.InternalHiringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/internal-hiring")
@CrossOrigin(origins = "*")
public class InternalHiringController {

    @Autowired
    private InternalHiringService internalHiringService;

    @GetMapping
    public List<InternalHiring> getAllCandidates() {
        return internalHiringService.getAllCandidates();
    }

    @GetMapping("/{id}")
    public InternalHiring getCandidateById(@PathVariable Integer id) {
        return internalHiringService.getCandidateById(id)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));
    }

    @PostMapping
    public InternalHiring saveCandidate(@RequestBody InternalHiring candidate) {
        return internalHiringService.saveCandidate(candidate);
    }

    @PutMapping("/{id}")
    public InternalHiring updateCandidate(@PathVariable Integer id,
                                          @RequestBody InternalHiring candidate) {
        return internalHiringService.updateCandidate(id, candidate);
    }

    @DeleteMapping("/{id}")
    public void deleteCandidate(@PathVariable Integer id) {
        internalHiringService.deleteCandidate(id);
    }
}