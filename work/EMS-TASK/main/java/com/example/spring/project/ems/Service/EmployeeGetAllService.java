package com.example.spring.project.ems.Service;

import com.example.spring.project.ems.DTO.EmployeeDTO;
import com.example.spring.project.ems.Mapper.EmployeeMapper;
import com.example.spring.project.ems.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeGetAllService {


    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream().map(employeeMapper::toDTO).toList();
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        return employeeRepository.findById(id).map(employeeMapper::toDTO);
    }
}
