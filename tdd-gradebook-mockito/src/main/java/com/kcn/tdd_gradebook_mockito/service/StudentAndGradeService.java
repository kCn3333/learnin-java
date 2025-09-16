package com.kcn.tdd_gradebook_mockito.service;

import com.kcn.tdd_gradebook_mockito.model.*;
import com.kcn.tdd_gradebook_mockito.repository.HistoryGradesDao;
import com.kcn.tdd_gradebook_mockito.repository.MathGradesDao;
import com.kcn.tdd_gradebook_mockito.repository.ScienceGradesDao;
import com.kcn.tdd_gradebook_mockito.repository.StudentDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentAndGradeService {

    private StudentDao studentDao;

    private MathGradesDao mathGradesDao;
    private ScienceGradesDao scienceGradesDao;
    private HistoryGradesDao historyGradesDao;

    public StudentAndGradeService(StudentDao studentDao, MathGradesDao mathGradesDao, ScienceGradesDao scienceGradesDao, HistoryGradesDao historyGradesDao) {
        this.studentDao = studentDao;
        this.mathGradesDao = mathGradesDao;
        this.scienceGradesDao = scienceGradesDao;
        this.historyGradesDao = historyGradesDao;

    }

    public void createStudent(String firstName, String lastName, String emailAddress){
        Student student=new Student(firstName,lastName,emailAddress);
        studentDao.save(student);

    }

    public boolean checkIsStudentNull(int id) {
        Optional<Student> student=studentDao.findById(id);
        return student.isPresent();
    }

    public void deleteStudent(int id) {
        if(checkIsStudentNull(id)){
            studentDao.deleteById(id);
            mathGradesDao.deleteByStudentId(id);
            historyGradesDao.deleteByStudentId(id);
            scienceGradesDao.deleteByStudentId(id);
        }

    }

    public Iterable<Student> getGradebook() {
        return studentDao.findAll();
    }

    public Gradebook getAllGradebook () {
        StudentGrades studentGrades=new StudentGrades();

        Iterable<Student> collegeStudents = studentDao.findAll();

        Iterable<MathGrade> mathGrades = mathGradesDao.findAll();

        Iterable<ScienceGrade> scienceGrades = scienceGradesDao.findAll();

        Iterable<HistoryGrade> historyGrades = historyGradesDao.findAll();

        Gradebook gradebook = new Gradebook();

        for (Student collegeStudent : collegeStudents) {
            List<Grade> mathGradesPerStudent = new ArrayList<>();
            List<Grade> scienceGradesPerStudent = new ArrayList<>();
            List<Grade> historyGradesPerStudent = new ArrayList<>();

            for (MathGrade grade : mathGrades) {
                if (grade.getStudentId() == collegeStudent.getId()) {
                    mathGradesPerStudent.add(grade);
                }
            }
            for (ScienceGrade grade : scienceGrades) {
                if (grade.getStudentId() == collegeStudent.getId()) {
                    scienceGradesPerStudent.add(grade);
                }
            }

            for (HistoryGrade grade : historyGrades) {
                if (grade.getStudentId() == collegeStudent.getId()) {
                    historyGradesPerStudent.add(grade);
                }
            }

            studentGrades.setMathGradeResults(mathGradesPerStudent);
            studentGrades.setScienceGradeResults(scienceGradesPerStudent);
            studentGrades.setHistoryGradeResults(historyGradesPerStudent);

            GradebookStudent gradebookCollegeStudent = new GradebookStudent(collegeStudent.getId(), collegeStudent.getFirstName(), collegeStudent.getLastName(),
                    collegeStudent.getEmailAddress(), studentGrades);

            gradebook.getStudents().add(gradebookCollegeStudent);
        }

        return gradebook;
    }

    public boolean createGrade(double grade, int studentId, String gradeType) {
        if (checkIsStudentNull(studentId) && grade >=0.0 && grade <=100.0 ){
            if(gradeType.equals("math")) {
                MathGrade mathGrade = new MathGrade(grade,studentId);
                mathGradesDao.save(mathGrade);
                return true;
            }
            if(gradeType.equals("science")) {
                ScienceGrade scienceGrade = new ScienceGrade(grade,studentId);
                scienceGradesDao.save(scienceGrade);
                return true;
            }
            if(gradeType.equals("history")) {
                HistoryGrade historyGrade = new HistoryGrade(grade,studentId);
                historyGradesDao.save(historyGrade);
                return true;
            }
        }
       return false;
    }

    public int deleteGrade(int gradeId, String gradeType) {
        if (gradeType.equals("math")){
            Optional<MathGrade> mathGrade=mathGradesDao.findById(gradeId);
                    if (mathGrade.isPresent()) {
                        mathGradesDao.deleteById(gradeId);
                        return mathGrade.get().getStudentId();
                    }

            }
        if (gradeType.equals("history")){
            Optional<HistoryGrade> historyGrade=historyGradesDao.findById(gradeId);
            if (historyGrade.isPresent()) {
                historyGradesDao.deleteById(gradeId);
                return historyGrade.get().getStudentId();
            }

        }
        if (gradeType.equals("science")){
            Optional<ScienceGrade> scienceGrade=scienceGradesDao.findById(gradeId);
            if (scienceGrade.isPresent()) {
                scienceGradesDao.deleteById(gradeId);
                return scienceGrade.get().getStudentId();
            }

        }
        return 0;


    }

    public GradebookStudent studentInformation(int id) {
        Optional<Student> student = studentDao.findById(id);
        if (student.isPresent()){
            Iterable<MathGrade> mathGrades=mathGradesDao.findGradeByStudentId(id);
            Iterable<ScienceGrade> scienceGrades=scienceGradesDao.findGradeByStudentId(id);
            Iterable<HistoryGrade> historyGrades=historyGradesDao.findGradeByStudentId(id);

            List<Grade> mathGradesList=new ArrayList<>();
            mathGrades.forEach(mathGradesList::add);

            List<Grade> scienceGradesList=new ArrayList<>();
            scienceGrades.forEach(scienceGradesList::add);

            List<Grade> historyGradesList=new ArrayList<>();
            historyGrades.forEach(historyGradesList::add);

            StudentGrades studentGrades=new StudentGrades();
            studentGrades.setHistoryGradeResults(historyGradesList);
            studentGrades.setMathGradeResults(mathGradesList);
            studentGrades.setScienceGradeResults(scienceGradesList);

            return new GradebookStudent(student.get().getId(),student.get().getFirstName(),student.get().getLastName(),student.get().getEmailAddress(),studentGrades);
        }
        return null;
    }

    public void configureStudentInformationModel(int studentId, Model model){
        GradebookStudent student=studentInformation(studentId);
        model.addAttribute("student", student);
        if(!student.getStudentGrades().getMathGradeResults().isEmpty()){
            model.addAttribute("mathAverage",student.getStudentGrades().findGradePointAverage(student.getStudentGrades().getMathGradeResults()));
        }
        else {
            model.addAttribute("mathAverage", "N/A");
        }
        if(!student.getStudentGrades().getScienceGradeResults().isEmpty()){
            model.addAttribute("scienceAverage",student.getStudentGrades().findGradePointAverage(student.getStudentGrades().getScienceGradeResults()));
        }
        else {
            model.addAttribute("scienceAverage", "N/A");
        }
        if(!student.getStudentGrades().getScienceGradeResults().isEmpty()){
            model.addAttribute("historyAverage",student.getStudentGrades().findGradePointAverage(student.getStudentGrades().getHistoryGradeResults()));
        }
        else {
            model.addAttribute("historyAverage", "N/A");
        }
    }
}
