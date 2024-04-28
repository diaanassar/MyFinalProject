package com.example.myfinalproject.Controller;

import com.example.myfinalproject.Entities.Student;
import com.example.myfinalproject.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping
    public List<Student> findAll(){
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public Student findStudentById(@PathVariable int id){
        return studentService.findById(id);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable int id, @RequestBody Student student){
        return studentService.updateStudent(id, student);
    }
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id){
         studentService.deleteStudent(id);
    }

    //Retrieve all students and the courses they are enrolled in:
    @GetMapping("/allStudentsAndCourses")
    public List<Student> getAllStudentsAndCourses(){
        return studentService.getAllStudentsAndCourses();
    }
//    @GetMapping("/studentsByCoursesCount/{count}")
//    public List<Student> getStudentsByCoursesCount(@PathVariable int count){
//       return (List<Student>) studentService.getStudentsByCoursesCount(count);
//    }
}
