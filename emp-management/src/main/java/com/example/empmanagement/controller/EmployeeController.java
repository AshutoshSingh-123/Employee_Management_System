package com.example.empmanagement.controller;

import com.example.empmanagement.entity.Employee;
import com.example.empmanagement.service.EmplyeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class EmployeeController {
    @Autowired
    EmplyeeService emplyeeService;
    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("employees",emplyeeService.findAll());
        return "home";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        emplyeeService.deleteById(Long.valueOf(id));
        return "redirect:/home";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable String id, Model model){
        Optional<Employee> employee=emplyeeService.findById(Long.valueOf(id));
        model.addAttribute("employee",employee.get());
        return "update";
    }

    @PostMapping("/updateProcess/{id}")
    public String updateProcess(@ModelAttribute Employee employee,@PathVariable String id){

        Employee employee1=emplyeeService.findById(Long.valueOf(id)).get();
        employee1.setId(employee.getId());
        employee1.setDept(employee.getDept());
        employee1.setSalary(employee.getSalary());
        employee1.setLastName(employee.getLastName());
        employee1.setFirstName(employee.getFirstName());
        emplyeeService.save(employee1);
        return "redirect:/home";
    }
}
