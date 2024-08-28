package com.example.spring.project.ems;

import com.example.spring.project.ems.Controller.CreateEmployee;
import com.example.spring.project.ems.DTO.EmployeeDTO;
import com.example.spring.project.ems.Service.EmployeeCreateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.closeTo;
import com.fasterxml.jackson.databind.ObjectMapper;

class CreateEmployeeTest {

    private MockMvc mockMvc;

    @Mock
    private EmployeeCreateService employeeService;

    @InjectMocks
    private CreateEmployee createEmployee;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(createEmployee).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testCreateEmployee() throws Exception {
        // Arrange
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(1L);
        employeeDTO.setName("John Doe");
        employeeDTO.setDepartment("JAVA");
        employeeDTO.setSalary(20000d);

        when(employeeService.createEmployee(any(EmployeeDTO.class))).thenReturn(employeeDTO);

        // Act & Assert
        mockMvc.perform(post("/api/employees1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(notNullValue())))
                .andExpect(jsonPath("$.name", is("John Doe")))
                .andExpect(jsonPath("$.department", is("JAVA")))
                .andExpect(jsonPath("$.salary", is(closeTo(20000d, 0.01))));
    }
}
