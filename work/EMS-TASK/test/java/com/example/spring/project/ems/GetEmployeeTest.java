package com.example.spring.project.ems;



import com.example.spring.project.ems.Controller.GetEmployee;
import com.example.spring.project.ems.DTO.EmployeeDTO;
import com.example.spring.project.ems.Service.EmployeeGetAllService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

class GetEmployeeTest {

    private MockMvc mockMvc;

    @Mock
    private EmployeeGetAllService employeeService;

    @InjectMocks
    private GetEmployee getEmployee;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(getEmployee).build();
    }

    @Test
    void testGetAllEmployees() throws Exception {
        // Arrange
        EmployeeDTO employee1 = new EmployeeDTO(1L, "John Doe", "Engineering", 50000d);
        EmployeeDTO employee2 = new EmployeeDTO(2L, "Jane Smith", "Marketing", 45000d);
        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(employee1, employee2));

        // Act & Assert
        mockMvc.perform(get("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("John Doe")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Jane Smith")));
    }

    @Test
    void testGetEmployeeById() throws Exception {
        // Arrange
        Long employeeId = 1L;
        EmployeeDTO employeeDTO = new EmployeeDTO(employeeId, "John Doe", "Engineering", 50000d);
        when(employeeService.getEmployeeById(employeeId)).thenReturn(Optional.of(employeeDTO));

        // Act & Assert
        mockMvc.perform(get("/api/employees/{id}", employeeId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("John Doe")))
                .andExpect(jsonPath("$.department", is("Engineering")));
    }

    @Test
    void testGetEmployeeById_NotFound() throws Exception {
        // Arrange
        Long employeeId = 1L;
        when(employeeService.getEmployeeById(employeeId)).thenReturn(Optional.empty());

        // Act & Assert
        mockMvc.perform(get("/api/employees/{id}", employeeId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
