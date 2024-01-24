package com.example.empmanagement.service;

import com.example.empmanagement.entity.Employee;
import com.example.empmanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmplyeeServiceImpl implements EmplyeeService {

    @Autowired
    EmployeeRepository employeeRepository;
    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);

    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public void update(Employee employee, Long id) {
            Optional<Employee> employee1=findById(id);
            if (employee1.isPresent()){
                employee.setId(id);
                save(employee);
            }
            else {
                save(employee);
            }
    }

    @Override
    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }

    @Override
    public void deleteById(Long id) {
            employeeRepository.deleteById(id);
    }
}
