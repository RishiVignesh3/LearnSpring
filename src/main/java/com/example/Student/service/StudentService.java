package com.example.Student.service;

import com.example.Student.dto.StudentWithCompanyResponse;
import com.example.Student.model.Company;
import com.example.Student.model.Student;
import com.example.Student.repository.CompanyRepository;
import com.example.Student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CompanyRepository companyRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public List<StudentWithCompanyResponse> getStudentsWithComDetails() {

        List<Student> students = studentRepository.findAll();

        return students.stream().map(student -> {
            Company company = companyRepository.findById(student.getCompanyId())
                    .orElse(null); // Handle company not found if necessary
            return new StudentWithCompanyResponse(student, company);
        }).collect(Collectors.toList());

    }

    public StudentWithCompanyResponse getStudentsWithComDetails(Long studentId, Long companyId) {

        Student student = studentRepository.findById(studentId).orElse(null);

        if(student == null) {
            throw new RuntimeException("No Students associated with this ID");
        }

        Company company = companyRepository.findById(companyId).orElse(null);

        return new StudentWithCompanyResponse(student, company);


    }

    public List<Student> getAllStudentsWithComUsingAnno() {

        List<Student> students = studentRepository.findAll();

        return students;
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> getStudentsByCompanyId(Long companyId) { return studentRepository.findAllByCompanyId(companyId); }

    public Student linkCompany(Long studentId, Long companyId) {

        Student student = studentRepository.findById(studentId).orElse(null);

        if(student != null) {
            student.setCompanyId(companyId);
            return studentRepository.save(student);
        }

        throw new RuntimeException("No user found");
    }
}
