package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Emp;
import com.example.demo.service.EmpService;

@RestController
@RequestMapping("/api/v1/employees")
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping
    public List<Emp> getAllEmps() {
        return empService.getAllEmps();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emp> getEmpById(@PathVariable Long id) {
        Optional<Emp> emp = empService.getEmpById(id);

        if (emp.isPresent()) {
            return ResponseEntity.ok(emp.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Emp createEmp(@RequestBody Emp emp) {
        return empService.createEmp(emp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Emp> updateEmp(@PathVariable Long id, @RequestBody Emp empDetails) {
        try {
            Emp updatedEmp = empService.updateEmp(id, empDetails);
            return ResponseEntity.ok(updatedEmp);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmp(@PathVariable Long id) {
        empService.deleteEmp(id);
        return ResponseEntity.noContent().build();
    }
}
