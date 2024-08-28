package com.example.spring.project.ems;



import com.example.spring.project.ems.DTO.EmployeeDTO;
import com.example.spring.project.ems.Entity.EmployeeEntity;
import com.example.spring.project.ems.Mapper.EmployeeMapper;
import com.example.spring.project.ems.Repositories.EmployeeRepository;
import com.example.spring.project.ems.Service.EmployeeGetAllService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EmployeeGetAllServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeGetAllService employeeGetAllService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEmployees() {
        // Arrange
        EmployeeEntity employeeEntity1 = new EmployeeEntity();
        employeeEntity1.setId(1L);
        employeeEntity1.setName("John Doe");

        EmployeeEntity employeeEntity2 = new EmployeeEntity();
        employeeEntity2.setId(2L);
        employeeEntity2.setName("Jane Doe");

        EmployeeDTO employeeDTO1 = new EmployeeDTO();
        employeeDTO1.setId(1L);
        employeeDTO1.setName("John Doe");

        EmployeeDTO employeeDTO2 = new EmployeeDTO();
        employeeDTO2.setId(2L);
        employeeDTO2.setName("Jane Doe");

        when(employeeRepository.findAll()).thenReturn(List.of(employeeEntity1, employeeEntity2));
        when(employeeMapper.toDTO(employeeEntity1)).thenReturn(employeeDTO1);
        when(employeeMapper.toDTO(employeeEntity2)).thenReturn(employeeDTO2);

        // Act
        List<EmployeeDTO> result = employeeGetAllService.getAllEmployees();

        // Assert
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals("Jane Doe", result.get(1).getName());

        verify(employeeRepository).findAll();
        verify(employeeMapper).toDTO(employeeEntity1);
        verify(employeeMapper).toDTO(employeeEntity2);
    }

    @Test
    void testGetEmployeeById() {
        // Arrange
        Long employeeId = 1L;
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(employeeId);
        employeeEntity.setName("John Doe");

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employeeId);
        employeeDTO.setName("John Doe");

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employeeEntity));
        when(employeeMapper.toDTO(employeeEntity)).thenReturn(employeeDTO);

        // Act
        Optional<EmployeeDTO> result = employeeGetAllService.getEmployeeById(employeeId);

        // Assert
        assertTrue(result.isPresent());
    }
}

