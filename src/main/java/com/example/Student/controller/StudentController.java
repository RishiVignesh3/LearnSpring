package com.example.Student.controller;

import com.example.Student.dto.LinkCompanyRequest;
import com.example.Student.dto.StudentDTO;
import com.example.Student.dto.StudentWithCompanyResponse;
import com.example.Student.model.Student;
import com.example.Student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/students")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/getStudentView")
    public String getAllStudentsView() {
        return "students";
    }

    @GetMapping("allByCompany/{companyId}")
    public ResponseEntity<List<Student>> getAllStudentsByCompanyId(@PathVariable Long companyId) {
        List<Student> students = studentService.getStudentsByCompanyId(companyId);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("withCompany")
    public ResponseEntity<List<StudentWithCompanyResponse>> getStudentsWithCompanyDetails() {
        List<StudentWithCompanyResponse> studentsWithComDetails = studentService.getStudentsWithComDetails();

        return new ResponseEntity<>(studentsWithComDetails, HttpStatus.OK);
    }

    @GetMapping("withCompany/{studentId}")
    public ResponseEntity<StudentWithCompanyResponse> getStudentWithCompanyDetails(@PathVariable Long studentId) {
        Student student = studentService.getStudentById(studentId);
        Long companyId = student.getCompanyId();
        StudentWithCompanyResponse studentsWithComDetails = studentService.getStudentsWithComDetails(studentId, companyId);

        return new ResponseEntity<>(studentsWithComDetails, HttpStatus.OK);
    }

    @GetMapping("studentsWithAnnotation")
    public ResponseEntity<List<Student>> getStudentsWithAnnotation() {
        List<Student> students = studentService.getAllStudentsWithComUsingAnno();

        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        if (student != null) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Object> createStudent(@ModelAttribute StudentDTO studentDTO) throws IOException {
        // Check if file is null
        MultipartFile file = studentDTO.getImage();
        if (file == null) {
            return new ResponseEntity<>("No file uploaded", HttpStatus.BAD_REQUEST);
        }

        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setCompanyId(studentDTO.getCompanyId());
        student.setAge(studentDTO.getAge());
        student.setPassword(studentDTO.getPassword());

        student.setImage(file.getBytes()); // Save the file

        Student savedStudent = studentService.saveStudent(student);

        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }


    @PatchMapping("/{studentId}/linkCompany")
    public ResponseEntity<Student> modifyStudent(
            @PathVariable Long studentId,
            @RequestBody LinkCompanyRequest linkCompany
    ) {
        Long companyId = linkCompany.getCompanyId();
        Student modifiedStudent = studentService.linkCompany(studentId, companyId);
        return new ResponseEntity<>(modifiedStudent, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
