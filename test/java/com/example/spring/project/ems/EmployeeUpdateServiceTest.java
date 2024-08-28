package com.example.spring.project.ems;



import com.example.spring.project.ems.DTO.EmployeeDTO;
import com.example.spring.project.ems.Entity.EmployeeEntity;
import com.example.spring.project.ems.Mapper.EmployeeMapper;
import com.example.spring.project.ems.Repositories.EmployeeRepository;
import com.example.spring.project.ems.Service.EmployeeUpdateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EmployeeUpdateServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeUpdateService employeeUpdateService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateEmployee_Success() {
        // Arrange
        Long employeeId = 1L;
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(employeeId);
        employeeEntity.setName("Old Name");
        employeeEntity.setDepartment("Old Department");
        employeeEntity.setSalary(30000d);

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employeeId);
        employeeDTO.setName("New Name");
        employeeDTO.setDepartment("New Department");
        employeeDTO.setSalary(50000d);

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employeeEntity));
        when(employeeRepository.save(employeeEntity)).thenReturn(employeeEntity);
        when(employeeMapper.toDTO(employeeEntity)).thenReturn(employeeDTO);

        // Act
        EmployeeDTO updatedEmployee = employeeUpdateService.updateEmployee(employeeId, employeeDTO);

        // Assert
        assertEquals("New Name", updatedEmployee.getName());
        assertEquals("New Department", updatedEmployee.getDepartment());
        assertEquals(50000d, updatedEmployee.getSalary());

        verify(employeeRepository).findById(employeeId);
        verify(employeeRepository).save(employeeEntity);
        verify(employeeMapper).toDTO(employeeEntity);
    }

    @Test
    void testUpdateEmployee_NotFound() {
        // Arrange
        Long employeeId = 1L;
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("New Name");
        employeeDTO.setDepartment("New Department");
        employeeDTO.setSalary(50000d);

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                employeeUpdateService.updateEmployee(employeeId, employeeDTO));

        assertEquals("Employee not found", exception.getMessage());

        verify(employeeRepository).findById(employeeId);
    }
}
