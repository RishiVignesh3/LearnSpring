package com.example.Student.controller;

import com.example.Student.model.Student;
import com.example.Student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/helloAll")
public class HelloAll {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public String getHelloAll(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "ReactExample";
    }
}
