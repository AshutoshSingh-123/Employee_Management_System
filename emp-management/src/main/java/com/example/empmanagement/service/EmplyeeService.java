package com.example.empmanagement.service;

import com.example.empmanagement.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmplyeeService {
    public void save(Employee employee);
    public List<Employee>  findAll();
    public Optional<Employee> findById(Long id);

    public void update(Employee employee,Long id);

    public void delete(Employee employee);
    public void deleteById(Long id);
}
