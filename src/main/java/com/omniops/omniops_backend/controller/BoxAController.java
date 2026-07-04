package com.omniops.omniops_backend.controller;

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

    @PostMapping
    public BoxA save(@RequestBody BoxA boxA) {
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