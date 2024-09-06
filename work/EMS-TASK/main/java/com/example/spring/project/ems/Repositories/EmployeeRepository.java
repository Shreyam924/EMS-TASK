package com.example.spring.project.ems.Repositories;

import com.example.spring.project.ems.Entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}

