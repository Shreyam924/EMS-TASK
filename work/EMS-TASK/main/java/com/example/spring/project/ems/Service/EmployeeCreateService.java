package com.example.spring.project.ems.Service;

import com.example.spring.project.ems.DTO.EmployeeDTO;
import com.example.spring.project.ems.Entity.EmployeeEntity;
import com.example.spring.project.ems.Mapper.EmployeeMapper;
import com.example.spring.project.ems.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeCreateService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        EmployeeEntity employee = employeeMapper.toEntity(employeeDTO);
        EmployeeEntity createdEmployee = employeeRepository.save(employee);
        return employeeMapper.toDTO(createdEmployee);
    }
}
