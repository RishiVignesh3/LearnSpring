package com.example.Student.service;

import com.example.Student.model.Company;
import com.example.Student.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.Id;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getCompanies() { return companyRepository.findAll();}

    public Company getCompany(Long id) { return companyRepository.findById(id).orElse(null);}

    public Company addCompany(Company company) {
//        Long id = company.getId();

//        Optional<Company> exists = companyRepository.findById(id);
////        if(id == null || exists.isPresent()) {
////            throw new EntityExistsException("Already Exists with same id");
////        }
        return companyRepository.save(company);

    }
}
