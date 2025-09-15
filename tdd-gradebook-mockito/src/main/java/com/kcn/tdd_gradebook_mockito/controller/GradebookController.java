package com.kcn.tdd_gradebook_mockito.controller;

import com.kcn.tdd_gradebook_mockito.model.Gradebook;
import com.kcn.tdd_gradebook_mockito.model.Student;
import com.kcn.tdd_gradebook_mockito.service.StudentAndGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GradebookController {

	@Autowired
	private Gradebook gradebook;

    @Autowired
    private StudentAndGradeService service;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getStudents(Model m) {
		Iterable<Student> students=service.getGradebook();
        m.addAttribute("students", students);
        return "index";
	}

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String createStudent(@ModelAttribute("student") Student student, Model model) {
        service.createStudent(student.getFirstName(), student.getLastName(), student.getEmailAddress());
        Iterable<Student> students=service.getGradebook();
        model.addAttribute("students", students);
        return "index";
    }

    @RequestMapping(value = "/delete/student/{id}", method = RequestMethod.GET)
    public String deleteStudent(@PathVariable("id") int id, Model model) {
        if(service.checkIsStudentNull(id)) {
            service.deleteStudent(id);
            Iterable<Student> students = service.getGradebook();
            model.addAttribute("students", students);
            return "index";
        }
        else return "error";
    }


	@GetMapping("/studentInformation/{id}")
    public String studentInformation(@PathVariable int id, Model model) {
        if(service.checkIsStudentNull(id)){
            service.configureStudentInformationModel(id,model);
            return "studentInformation";
        }
        else return "error";

    }

    @RequestMapping(value = "/grades", method = RequestMethod.POST)
    public String createGrade(@RequestParam("grade") double grade, @RequestParam("gradeType") String gradeType, @RequestParam("studentId") int studentId, Model model) {
        if(service.checkIsStudentNull(studentId)){
            if(service.createGrade(grade,studentId,gradeType)){
                service.configureStudentInformationModel(studentId,model);
                return "studentInformation";
            }
            else return "error";
        }
        else return "error";
    }

    @RequestMapping(value = "/grades/{id}/{gradeType}", method = RequestMethod.GET)
    public String deleteGrade(@PathVariable("id") int id, @PathVariable("gradeType") String gradeType, Model model){

            int studentId=service.deleteGrade(id,gradeType);
            if(studentId!=0){
                service.configureStudentInformationModel(studentId, model);
                return "studentInformation";
            }
            return "error";
    }

}
