package com.example.myfinalproject.Service;

import com.example.myfinalproject.Entities.Course;
import com.example.myfinalproject.Entities.Course;
import com.example.myfinalproject.Repository.CourseRepository;
import com.example.myfinalproject.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course findById(int id) {
        return courseRepository.findById(id).orElse(null);
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(int id, Course course) {
        Course existingCourse = courseRepository.findById(id).orElse(null);
        existingCourse.setName(course.getName());
        existingCourse.setInstructor(course.getInstructor());
        return courseRepository.save(existingCourse);
    }

    public void deleteCourse(int id) {
        courseRepository.delete(
                findById(id)
        );
    }
}
