package com.kcn.tdd_gradebook_mockito;

import com.kcn.tdd_gradebook_mockito.model.GradebookStudent;
import com.kcn.tdd_gradebook_mockito.model.Student;
import com.kcn.tdd_gradebook_mockito.repository.MathGradesDao;
import com.kcn.tdd_gradebook_mockito.repository.StudentDao;
import com.kcn.tdd_gradebook_mockito.service.StudentAndGradeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@SpringBootTest
public class GradebookControllerTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Value("${sql.script.create.student}")
    String sqlAddStudent;
    @Value("${sql.script.create.math.grade}")
    String sqlAddMathGrade;
    @Value("${sql.script.create.history.grade}")
    String sqlAddHistoryGrade;
    @Value("${sql.script.create.science.grade}")
    String sqlAddScienceGrade;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private StudentAndGradeService serviceMock;

    @Autowired
    private StudentDao studentDao;
    @Autowired
    private StudentAndGradeService studentAndGradeService;
    @Autowired
    private MathGradesDao mathGradesDao;

    @BeforeAll
    static void setup(){
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        mockRequest.setParameter("firstname", "Bob");
        mockRequest.setParameter("lastname", "Bobsky");
        mockRequest.setParameter("emailAddress", "bob@email.com");
    }


    @BeforeEach
    void insertSampleData(){
        jdbcTemplate.execute(sqlAddStudent);

        jdbcTemplate.execute(sqlAddMathGrade);
        jdbcTemplate.execute(sqlAddHistoryGrade);
        jdbcTemplate.execute(sqlAddScienceGrade);
    }

    @AfterEach
    void cleanSampleData(){
        jdbcTemplate.execute("DELETE FROM math_grade");
        jdbcTemplate.execute("DELETE FROM science_grade");
        jdbcTemplate.execute("DELETE FROM history_grade");
        jdbcTemplate.execute("DELETE FROM student");

        jdbcTemplate.execute("ALTER TABLE student ALTER COLUMN id RESTART WITH 1");
        jdbcTemplate.execute("ALTER TABLE math_grade ALTER COLUMN id RESTART WITH 1");
        jdbcTemplate.execute("ALTER TABLE science_grade ALTER COLUMN id RESTART WITH 1");
        jdbcTemplate.execute("ALTER TABLE history_grade ALTER COLUMN id RESTART WITH 1");
    }

    @Test
    void getStudentHttpRequest() throws Exception {
        Student student1=new Student("Eric", "Ericovsky", "eric@email.com");
        Student student2=new Student("Chris","Robson", "chris@email.com");

        List<Student> StudentList =new ArrayList<>(Arrays.asList(student1,student2));

        when(serviceMock.getGradebook()).thenReturn(StudentList);

        assertIterableEquals(StudentList, serviceMock.getGradebook());

        MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(status().isOk()).andReturn();

        ModelAndView modelAndView=mvcResult.getModelAndView();

        assertNotNull(modelAndView);
        ModelAndViewAssert.assertViewName(modelAndView,"index");


    }

    @Test
    void createStudentHttpRequest() throws Exception {

        Student student1 = new Student("Eric", "Ericovsky", "eric@email.com");
        List<Student> studentList = new ArrayList<>(List.of(student1));

        when(serviceMock.getGradebook()).thenReturn(studentList);

        assertIterableEquals(studentList, serviceMock.getGradebook());

        MvcResult mvcResult = this.mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("firstName", "Eric")
                        .param("lastName", "Ericovsky")
                        .param("emailAddress", "eric@email.com"))
                .andExpect(status().isOk())
                .andReturn();

        ModelAndView modelAndView = mvcResult.getModelAndView();
        assertNotNull(modelAndView);
        ModelAndViewAssert.assertViewName(modelAndView, "index");

        Student verifyStudent = studentDao.findByEmailAddress("eric@email.com");
        assertNotNull(verifyStudent, "Student powinien istnieć w bazie");
        assertEquals("Eric", verifyStudent.getFirstName());
        assertEquals("Ericovsky", verifyStudent.getLastName());
    }


    @Test
    void deleteStudentHttpRequest() throws Exception {
        assertTrue(studentDao.findById(1).isPresent());

        MvcResult mvcResult=this.mockMvc.perform(MockMvcRequestBuilders.get("/delete/student/{id}",1))
                .andExpect(status().isOk()).andReturn();

        ModelAndView modelAndView=mvcResult.getModelAndView();
        assertNotNull(modelAndView);
        ModelAndViewAssert.assertViewName(modelAndView,"index");

        assertFalse(studentDao.findById(1).isPresent());
    }

    @Test
    void deleteStudentHttpRequestErrorPage() throws Exception {
        assertFalse(studentDao.findById(0).isPresent());

        MvcResult mvcResult=this.mockMvc.perform(MockMvcRequestBuilders.get("/delete/student/{id}",0))
                .andExpect(status().isOk()).andReturn();

        ModelAndView modelAndView=mvcResult.getModelAndView();
        assertNotNull(modelAndView);
        ModelAndViewAssert.assertViewName(modelAndView,"error");

    }

    @Test
    void studentInfoHttpRequest() throws Exception {
        assertTrue(studentDao.findById(1).isPresent());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/studentInformation/{id}", 1))
                .andExpect(status().isOk())
                .andReturn();

        ModelAndView modelAndView = mvcResult.getModelAndView();
        assertNotNull(modelAndView);
        ModelAndViewAssert.assertViewName(modelAndView, "studentInformation");

        assertTrue(modelAndView.getModel().containsKey("student"));

        Object studentObj = modelAndView.getModel().get("student");
        assertNotNull(studentObj);
        assertInstanceOf(GradebookStudent.class, studentObj);

        GradebookStudent student = (GradebookStudent) studentObj;
        assertEquals(1, student.getId());
        assertEquals("Boby", student.getFirstName());
    }


    @Test
    void studentInformationHttpDoesNotExistRequest() throws Exception {
        assertFalse(studentDao.findById(121).isPresent());

        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/studentInformation/{id}",121)).andExpect(status().isOk()).andReturn();
        ModelAndView modelAndView=mvcResult.getModelAndView();
        assertNotNull(modelAndView);
        ModelAndViewAssert.assertViewName(modelAndView,"error");
    }

    @Test
    void createValidGrade() throws Exception {
        assertTrue(studentDao.findById(1).isPresent());

        GradebookStudent studentBefore = studentAndGradeService.studentInformation(1);
        assertEquals(1, studentBefore.getStudentGrades().getMathGradeResults().size());

        MvcResult mvcResult = mockMvc.perform(post("/grades")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("grade", "5.0")
                        .param("gradeType", "math")
                        .param("studentId", "1"))
                .andExpect(status().isOk())
                .andReturn();

        ModelAndView modelAndView = mvcResult.getModelAndView();
        assertNotNull(modelAndView);
        ModelAndViewAssert.assertViewName(modelAndView, "studentInformation");

        GradebookStudent studentAfter = studentAndGradeService.studentInformation(1);
        assertEquals(2, studentAfter.getStudentGrades().getMathGradeResults().size());
    }


    @Test
    void createInvalidGradeResponse() throws Exception {
        assertTrue(studentDao.findById(1).isPresent());

        GradebookStudent student=studentAndGradeService.studentInformation(1);

        assertEquals(1, student.getStudentGrades().getMathGradeResults().size());

        MvcResult mvcResult=mockMvc.perform(post("/grades").contentType(MediaType.APPLICATION_JSON)
                        .param("grade", "-5.0")
                        .param("gradeType", "math")
                        .param("studentId", "1"))
                .andExpect(status().isOk()).andReturn();
        ModelAndView modelAndView=mvcResult.getModelAndView();
        assertNotNull(modelAndView);
        ModelAndViewAssert.assertViewName(modelAndView,"error");

        assertEquals(1,studentAndGradeService.studentInformation(1).getStudentGrades().getMathGradeResults().size());

    }

    @Test
    void createInvalidGradeGradeTypeDoesNotExistResponse() throws Exception {
        assertTrue(studentDao.findById(1).isPresent());

        MvcResult mvcResult=mockMvc.perform(post("/grades").contentType(MediaType.APPLICATION_JSON)
                        .param("grade", "5.0")
                        .param("gradeType", "physics")
                        .param("studentId", "1"))
                .andExpect(status().isOk()).andReturn();
        ModelAndView modelAndView=mvcResult.getModelAndView();
        assertNotNull(modelAndView);
        ModelAndViewAssert.assertViewName(modelAndView,"error");
    }

    @Test
    void createGradeStudentDoesNotExistResponse() throws Exception {
        assertFalse(studentDao.findById(0).isPresent());

        MvcResult mvcResult=mockMvc.perform(post("/grades").contentType(MediaType.APPLICATION_JSON)
                        .param("grade", "5.0")
                        .param("gradeType", "history")
                        .param("studentId", "0"))
                .andExpect(status().isOk()).andReturn();
        ModelAndView modelAndView=mvcResult.getModelAndView();
        assertNotNull(modelAndView);
        ModelAndViewAssert.assertViewName(modelAndView,"error");
    }

    @Test
    void deleteValidGradeHttpRequest() throws Exception{

        assertTrue(mathGradesDao.findById(1).isPresent());

        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/grades/{id}/{gradeType}", 1,"math"))
                .andExpect(status().isOk()).andReturn();
        ModelAndView modelAndView=mvcResult.getModelAndView();
        assertNotNull(modelAndView);
        ModelAndViewAssert.assertViewName(modelAndView,"studentInformation");

        assertFalse(mathGradesDao.findById(1).isPresent());

    }

    @Test
    void deleteInvalidGradeHttpRequest() throws Exception{

        assertFalse(mathGradesDao.findById(0).isPresent());

        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/grades/{id}/{gradeType}", 0,"math"))
                .andExpect(status().isOk()).andReturn();
        ModelAndView modelAndView=mvcResult.getModelAndView();
        assertNotNull(modelAndView);
        ModelAndViewAssert.assertViewName(modelAndView,"error");
    }

    @Test
    void deleteInvalidGradeSubjectHttpRequest() throws Exception{

        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/grades/{id}/{gradeType}", 1,"literature"))
                .andExpect(status().isOk()).andReturn();
        ModelAndView modelAndView=mvcResult.getModelAndView();
        assertNotNull(modelAndView);
        ModelAndViewAssert.assertViewName(modelAndView,"error");
    }


}
