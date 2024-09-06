package com.example.spring.project.ems;


import com.example.spring.project.ems.Repositories.EmployeeRepository;
import com.example.spring.project.ems.Service.DeleteEmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

class DeleteEmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private DeleteEmployeeService deleteEmployeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteEmployee() {
        // Arrange
        Long employeeId = 1L;

        // Mock the behavior of the repository
        doNothing().when(employeeRepository).deleteById(employeeId);

        // Act
        deleteEmployeeService.deleteEmployee(employeeId);

        // Assert
        verify(employeeRepository).deleteById(employeeId);
    }
}
