package com.example.spring.project.ems;



import com.example.spring.project.ems.Controller.UpdateEmployee;
import com.example.spring.project.ems.DTO.EmployeeDTO;
import com.example.spring.project.ems.Service.EmployeeUpdateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;
import com.fasterxml.jackson.databind.ObjectMapper;

class UpdateEmployeeTest {

    private MockMvc mockMvc;

    @Mock
    private EmployeeUpdateService employeeService;

    @InjectMocks
    private UpdateEmployee updateEmployee;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(updateEmployee).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testUpdateEmployee() throws Exception {
        // Arrange
        Long employeeId = 1L;
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employeeId);
        employeeDTO.setName("John Doe");
        employeeDTO.setDepartment("HR");
        employeeDTO.setSalary(55000d);

        when(employeeService.updateEmployee(eq(employeeId), any(EmployeeDTO.class))).thenReturn(employeeDTO);

        // Act & Assert
        mockMvc.perform(put("/api/employees/{id}", employeeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("John Doe")))
                .andExpect(jsonPath("$.department", is("HR")))
                .andExpect(jsonPath("$.salary", is(55000d)));
    }

    @Test
    void testUpdateEmployee_NotFound() throws Exception {
        // Arrange
        Long employeeId = 1L;
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("John Doe");
        employeeDTO.setDepartment("HR");
        employeeDTO.setSalary(55000d);

        when(employeeService.updateEmployee(eq(employeeId), any(EmployeeDTO.class))).thenReturn(null);

        // Act & Assert
        mockMvc.perform(put("/api/employees/{id}", employeeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeDTO)))
                .andExpect(status().isNotFound());
    }
}
