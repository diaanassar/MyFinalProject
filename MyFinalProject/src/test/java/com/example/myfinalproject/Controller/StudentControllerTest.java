package com.example.myfinalproject.Controller;

import com.example.myfinalproject.Entities.Course;
import com.example.myfinalproject.Entities.Student;
import com.example.myfinalproject.Service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class StudentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;


    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }

    @Test
    public void testFindAll() throws Exception {
        List<Student> students = Arrays.asList(new Student(1, "student1@example.com", "password1", "John Doe", 20,
                Arrays.asList(
                        new Course(1, "Mathematics", "Professor X"),
                        new Course(2, "Physics", "Professor Y")
                )), new Student(2, "student2@example.com", "password2", "Alice Smith", 22,
                Arrays.asList(
                        new Course(3, "Computer Science", "Professor Z"),
                        new Course(4, "Literature", "Professor W"),
                        new Course(5, "History", "Professor V")
                )));
        when(studentService.findAll()).thenReturn(students);

        mockMvc.perform(get("/student"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("John"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Jane"));

        verify(studentService, times(1)).findAll();
        verifyNoMoreInteractions(studentService);
    }

    @Test
    public void testFindStudentById() throws Exception {
        int studentId = 1;
        Student student = new Student(1, "student1@example.com", "password1", "John Doe", 20,
                Arrays.asList(
                        new Course(1, "Mathematics", "Professor X"),
                        new Course(2, "Physics", "Professor Y")
                ));
        when(studentService.findById(studentId)).thenReturn(student);

        mockMvc.perform(get("/student/{id}", studentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(studentId))
                .andExpect(jsonPath("$.name").value("John"));

        verify(studentService, times(1)).findById(studentId);
        verifyNoMoreInteractions(studentService);
    }

    @Test
    public void testCreateStudent() throws Exception {
        Student studentToCreate = new Student(1, "student1@example.com", "password1", "John Doe", 20,
                Arrays.asList(
                        new Course(1, "Mathematics", "Professor X"),
                        new Course(2, "Physics", "Professor Y")
                ));
        Student createdStudent = new Student(1, "student1@example.com", "password1", "John Doe", 20,
                Arrays.asList(
                        new Course(1, "Mathematics", "Professor X"),
                        new Course(2, "Physics", "Professor Y")
                ));

        when(studentService.createStudent(any(Student.class))).thenReturn(createdStudent);

        mockMvc.perform(post("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"Alice\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Alice"));

        verify(studentService, times(1)).createStudent(any(Student.class));
        verifyNoMoreInteractions(studentService);
    }
}
