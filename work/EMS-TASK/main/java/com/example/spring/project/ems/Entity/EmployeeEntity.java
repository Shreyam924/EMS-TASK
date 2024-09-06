package com.example.spring.project.ems.Entity;

import com.example.spring.project.ems.DTO.EmployeeDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees3")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String department;

    @Column(nullable = false)
    private Double salary;

    public static EmployeeEntity mapToEmployee(EmployeeDTO employeeDto) {
        return new EmployeeEntity(
                employeeDto.getId(),
                employeeDto.getName(),
                employeeDto.getDepartment(),
                employeeDto.getSalary()
        );
    }
}

