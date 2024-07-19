package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Emp;
import com.example.demo.repository.EmpRepository;

@Service
public class EmpService {

    @Autowired
    private EmpRepository empRepository;

    public List<Emp> getAllEmps() {
        return empRepository.findAll();
    }

    public Optional<Emp> getEmpById(Long id) {
        return empRepository.findById(id);
    }

    public Emp createEmp(Emp emp) {
        return empRepository.save(emp);
    }

    public Emp updateEmp(Long id, Emp empDetails) {
        Optional<Emp> empOptional = empRepository.findById(id);

        if (empOptional.isPresent()) {
            Emp emp = empOptional.get();
            emp.setName(empDetails.getName());
            return empRepository.save(emp);
        } else {
            throw new RuntimeException("Emp not found with id " + id);
        }
    }

    public void deleteEmp(Long id) {
        empRepository.deleteById(id);
    }
}
