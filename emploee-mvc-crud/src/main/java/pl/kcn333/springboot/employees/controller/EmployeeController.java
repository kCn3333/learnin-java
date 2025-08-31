package pl.kcn333.springboot.employees.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.kcn333.springboot.employees.entity.Employee;
import pl.kcn333.springboot.employees.service.EmployeeService;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String listEmployees(Model model){
        List<Employee> employeeList =employeeService.findAll();
        model.addAttribute("employees",employeeList);
        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String formEmployee(Model model){
        model.addAttribute("employee", new Employee());
        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.save(employee);
        return "redirect:/employees/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("id") int id, Model model){
        model.addAttribute("employee", employeeService.findById(id));
        return "employees/employee-form";
    }
    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("id") int id){
        employeeService.deleteById(id);
        return "redirect:/employees/list";
    }




}
