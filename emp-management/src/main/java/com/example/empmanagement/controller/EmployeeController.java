package com.example.empmanagement.controller;

import com.example.empmanagement.entity.Employee;
import com.example.empmanagement.service.EmplyeeService;
import com.example.empmanagement.service.SaveObjectToJSON;
import com.example.empmanagement.util.RecentActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {
    @Autowired
    EmplyeeService emplyeeService;

    @Autowired
    SaveObjectToJSON saveObjectToJSON;
    @GetMapping("/index")
    public String index(Model model) throws IOException {
        List<RecentActivity> recentActivities=saveObjectToJSON.getAllActivities();
        model.addAttribute("employees",emplyeeService.findAll().size());
        model.addAttribute("recentActivities",recentActivities);
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("employees",emplyeeService.findAll());
        return "list";
    }
    @GetMapping("/save")
    public String save(Model model){
        model.addAttribute("employee",new Employee());
        return "ui-forms";
    }
    @PostMapping("/saveProcess")
    public String saveProcess(@ModelAttribute Employee employee) throws IOException {
        RecentActivity recentActivity=RecentActivity.builder()
                .activity("Employee Added")
                .actionOn(employee.getFirstName())
                .build();
        saveObjectToJSON.saveActivity(recentActivity);
        emplyeeService.save(employee);
        return "redirect:/home";
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
