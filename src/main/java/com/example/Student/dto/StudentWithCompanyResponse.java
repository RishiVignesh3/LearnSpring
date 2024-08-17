package com.example.Student.dto;

        import com.example.Student.model.Company;
        import com.example.Student.model.Student;
        import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentWithCompanyResponse {
    private Long id;
    private String name;
    private int age;
    private String email;
    private Company company;

    // Additional constructor to create the DTO from a Student and a Company
    public StudentWithCompanyResponse(Student student, Company company) {
        this.id = student.getId();
        this.name = student.getName();
        this.age = student.getAge();
        this.email = student.getEmail();
        this.company = company;
    }
}
