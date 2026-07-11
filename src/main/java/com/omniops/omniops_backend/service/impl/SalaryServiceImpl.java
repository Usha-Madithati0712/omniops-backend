package com.omniops.omniops_backend.service.impl;

import com.omniops.omniops_backend.entity.Salary;
import com.omniops.omniops_backend.repository.SalaryRepository;
import com.omniops.omniops_backend.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaryServiceImpl implements SalaryService {

    @Autowired
    private SalaryRepository salaryRepository;

    @Override
    public List<Salary> getAllSalaries() {
        return salaryRepository.findAll();
    }

    @Override
    public Optional<Salary> getSalaryById(Integer id) {
        return salaryRepository.findById(id);
    }

    @Override
    public Salary saveSalary(Salary salary) {
        return salaryRepository.save(salary);
    }

    @Override
    public Salary updateSalary(Integer id, Salary salary) {

        Salary existingSalary = salaryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Salary record not found"));

        existingSalary.setEmployeeId(salary.getEmployeeId());
        existingSalary.setEmployeeName(salary.getEmployeeName());
        existingSalary.setDepartment(salary.getDepartment());
        existingSalary.setDesignation(salary.getDesignation());
        existingSalary.setMonth(salary.getMonth());

        existingSalary.setBasicSalary(salary.getBasicSalary());
        existingSalary.setHra(salary.getHra());
        existingSalary.setAllowances(salary.getAllowances());
        existingSalary.setBonus(salary.getBonus());
        existingSalary.setDeductions(salary.getDeductions());
        existingSalary.setNetSalary(salary.getNetSalary());

        existingSalary.setSalaryStatus(salary.getSalaryStatus());
        existingSalary.setRemarks(salary.getRemarks());

        return salaryRepository.save(existingSalary);
    }

    @Override
    public void deleteSalary(Integer id) {
        salaryRepository.deleteById(id);
    }
}