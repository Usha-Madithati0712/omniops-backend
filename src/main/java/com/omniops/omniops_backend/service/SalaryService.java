
package com.omniops.omniops_backend.service;

import com.omniops.omniops_backend.entity.Salary;

import java.util.List;
import java.util.Optional;

public interface SalaryService {

    List<Salary> getAllSalaries();

    Optional<Salary> getSalaryById(Integer id);

    Salary saveSalary(Salary salary);

    Salary updateSalary(Integer id, Salary salary);

    void deleteSalary(Integer id);
}