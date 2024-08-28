package com.example.spring.project.ems;



import com.example.spring.project.ems.Controller.DeleteEmployee;
import com.example.spring.project.ems.Service.DeleteEmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DeleteEmployeeTest {

    private MockMvc mockMvc;

    @Mock
    private DeleteEmployeeService employeeService;

    @InjectMocks
    private DeleteEmployee deleteEmployee;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(deleteEmployee).build();
    }

    @Test
    void testDeleteEmployee() throws Exception {
        // Arrange
        Long employeeId = 1L;
        doNothing().when(employeeService).deleteEmployee(employeeId);

        // Act & Assert
        mockMvc.perform(delete("/api/employees/{id}", employeeId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        // Verify that the deleteEmployee method in the service was called
        verify(employeeService).deleteEmployee(employeeId);
    }
}
