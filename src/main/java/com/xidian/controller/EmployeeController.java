package com.xidian.controller;

import com.xidian.domain.Employee;
import com.xidian.domain.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class EmployeeController {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }



    @RequestMapping(value={"/employees"}, method= RequestMethod.GET)
    public List<Employee> getUserList() {
        return employeeRepository.findAll();
    }

    @RequestMapping(value={"/add"}, method= RequestMethod.POST)
    public String addEmployee() {
        employeeRepository.save(new Employee(0,"小明",20,"男"));
        employeeRepository.save(new Employee(1,"小红",19,"女"));
        employeeRepository.save(new Employee(2,"小智",15,"男"));
        employeeRepository.save(new Employee(3,"小刚",16,"男"));
        employeeRepository.save(new Employee(4,"小霞",15,"女"));
        return "success";
    }


    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteEmployee(@PathVariable int id) {
        employeeRepository.delete(employeeRepository.findEmployee(id));
        return "success";
    }


    @RequestMapping(value="/{id}", method= RequestMethod.PUT)
    public String updateEmployee(@PathVariable int id) {
        employeeRepository.findEmployee(id).setAge(18);
        return "success";
    }

}
