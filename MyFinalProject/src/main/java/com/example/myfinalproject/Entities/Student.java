package com.example.myfinalproject.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String password;
    private String name;
    private int age;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private List<Course> courses;

    public Student(int id, String email, String password, String name, int age, List<Course> courses) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.age = age;
        this.courses = courses;
    }
}
