package com.omniops.omniops_backend.controller;

import com.omniops.omniops_backend.entity.Salary;
import com.omniops.omniops_backend.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salaries")
@CrossOrigin(origins = "*")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @GetMapping
    public List<Salary> getAllSalaries() {
        return salaryService.getAllSalaries();
    }

    @GetMapping("/{id}")
    public Salary getSalaryById(@PathVariable Integer id) {
        return salaryService.getSalaryById(id)
                .orElseThrow(() -> new RuntimeException("Salary not found"));
    }

    @PostMapping
    public Salary saveSalary(@RequestBody Salary salary) {
        return salaryService.saveSalary(salary);
    }

    @PutMapping("/{id}")
    public Salary updateSalary(@PathVariable Integer id,
                               @RequestBody Salary salary) {
        return salaryService.updateSalary(id, salary);
    }

    @DeleteMapping("/{id}")
    public void deleteSalary(@PathVariable Integer id) {
        salaryService.deleteSalary(id);
    }
}