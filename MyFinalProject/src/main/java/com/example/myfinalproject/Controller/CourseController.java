package com.example.myfinalproject.Controller;

import com.example.myfinalproject.Entities.Course;
import com.example.myfinalproject.Service.CourseService;
import com.example.myfinalproject.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;
    @GetMapping
    public List<Course> findAll(){
        return courseService.findAll();
    }

    @GetMapping("/{id}")
    public Course findCourseById(@PathVariable int id){
        return courseService.findById(id);
    }

    @PostMapping
    public Course createCourse(@RequestBody Course course){
        return courseService.createCourse(course);
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable int id, @RequestBody Course course){
        return courseService.updateCourse(id, course);
    }
    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable int id){
        courseService.deleteCourse(id);
    }
}
