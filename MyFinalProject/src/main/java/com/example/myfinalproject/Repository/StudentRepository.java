package com.example.myfinalproject.Repository;

import com.example.myfinalproject.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository <Student, Integer> {
//    @Query(value = "select * from student JOIN course ON student.id = course.student_id", nativeQuery = true)
//    List<Student> getAllStudentsAndCourses();

    @Query(value = "SELECT s.* FROM student s INNER JOIN course c ON s.id = c.student_id GROUP BY s.id HAVING COUNT(c.id) <= 5", nativeQuery = true)
    List<Student> getAllStudentsAndCourses();

    public Student getStudentByEmail(String email);

}
