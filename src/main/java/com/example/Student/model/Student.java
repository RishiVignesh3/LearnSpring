package com.example.Student.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;
    private String email;
    private String password;
    @Lob
    private byte[] image;
    @Column(name = "company_id", insertable = false, updatable = false)
    private Long companyId;

    @ManyToOne
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private Company company;

    public Student() {};

    public Student(String name, int age, String email, Long companyId, Company company, String password, byte[] image) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.companyId = companyId;
        this.company = company;
        this.password = password;
        this.image = image;
    }

    public String getName(){
        return name;
    }

    public Long getId(){
        return id;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public int getAge(){
        return age;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public Company getCompany() {
        return company;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
