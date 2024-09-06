package com.example.spring.project.ems.Service;

import com.example.spring.project.ems.DTO.EmployeeDTO;
import com.example.spring.project.ems.Entity.EmployeeEntity;
import com.example.spring.project.ems.Mapper.EmployeeMapper;
import com.example.spring.project.ems.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeUpdateService {


    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        EmployeeEntity employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        employee.setName(employeeDTO.getName());
        employee.setDepartment(employeeDTO.getDepartment());
        employee.setSalary(employeeDTO.getSalary());
        return employeeMapper.toDTO(employeeRepository.save(employee));
    }

}
