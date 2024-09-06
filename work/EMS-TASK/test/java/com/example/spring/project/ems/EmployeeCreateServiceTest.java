package com.example.spring.project.ems;



import com.example.spring.project.ems.DTO.EmployeeDTO;
import com.example.spring.project.ems.Entity.EmployeeEntity;
import com.example.spring.project.ems.Mapper.EmployeeMapper;
import com.example.spring.project.ems.Repositories.EmployeeRepository;
import com.example.spring.project.ems.Service.EmployeeCreateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeCreateServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeCreateService employeeCreateService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateEmployee() {
        // Arrange
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("John Doe");
        employeeDTO.setDepartment("HR");
        employeeDTO.setSalary(55000d);

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName("John Doe");
        employeeEntity.setDepartment("HR");
        employeeEntity.setSalary(55000d);

        EmployeeEntity savedEmployeeEntity = new EmployeeEntity();
        savedEmployeeEntity.setId(1L);
        savedEmployeeEntity.setName("John Doe");
        savedEmployeeEntity.setDepartment("HR");
        savedEmployeeEntity.setSalary(55000d);

        EmployeeDTO savedEmployeeDTO = new EmployeeDTO();
        savedEmployeeDTO.setId(1L);
        savedEmployeeDTO.setName("John Doe");
        savedEmployeeDTO.setDepartment("HR");
        savedEmployeeDTO.setSalary(55000d);

        when(employeeMapper.toEntity(employeeDTO)).thenReturn(employeeEntity);
        when(employeeRepository.save(employeeEntity)).thenReturn(savedEmployeeEntity);
        when(employeeMapper.toDTO(savedEmployeeEntity)).thenReturn(savedEmployeeDTO);

        // Act
        EmployeeDTO result = employeeCreateService.createEmployee(employeeDTO);

        // Assert
        assertEquals(savedEmployeeDTO.getId(), result.getId());
        assertEquals(savedEmployeeDTO.getName(), result.getName());
        assertEquals(savedEmployeeDTO.getDepartment(), result.getDepartment());
        assertEquals(savedEmployeeDTO.getSalary(), result.getSalary());

        verify(employeeMapper).toEntity(employeeDTO);
        verify(employeeRepository).save(employeeEntity);
        verify(employeeMapper).toDTO(savedEmployeeEntity);
    }
}

