package com.example.myfinalproject.Service;

import com.example.myfinalproject.Entities.Course;
import com.example.myfinalproject.Entities.Student;
import com.example.myfinalproject.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class StudentService implements ApplicationRunner {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(int id, Student student) {
        Student existingStudent = studentRepository.findById(id).orElse(null);
        existingStudent.setAge(student.getAge());
        existingStudent.setName(student.getName());
        existingStudent.setCourses(student.getCourses());
        return studentRepository.save(existingStudent);
    }

    public void deleteStudent(int id) {
         studentRepository.delete(
                findById(id)
        );
    }

    public List<Student> getAllStudentsAndCourses() {
        return studentRepository.getAllStudentsAndCourses();
    }

//    public Student getStudentsByCoursesCount(int count) {
//       return (Student) studentRepository.getStudentsByCoursesCount(count);
//    }
    private void addDummyData() {
        studentRepository.saveAll(
                Arrays.asList(
                        new Student(1, "student1@example.com", passwordEncoder.encode("password1"), "John Doe", 20,
                                Arrays.asList(
                                        new Course(1, "Mathematics", "Professor X"),
                                        new Course(2, "Physics", "Professor Y")
                                )),
                        new Student(2, "student2@example.com", passwordEncoder.encode("password2"), "Alice Smith", 22,
                                Arrays.asList(
                                        new Course(3, "Computer Science", "Professor Z"),
                                        new Course(4, "Literature", "Professor W"),
                                        new Course(5, "History", "Professor V")
                                )),
                        new Student(3, "student3@example.com", passwordEncoder.encode("password3"), "Bob Johnson", 21,
                                Arrays.asList(
                                        new Course(6, "Biology", "Professor U")
                                )
                        )
                )
        );

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        addDummyData();
    }
}