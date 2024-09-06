package com.example.spring.project.ems.Mapper;

import com.example.spring.project.ems.DTO.EmployeeDTO;
import com.example.spring.project.ems.Entity.EmployeeEntity;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public EmployeeDTO toDTO(EmployeeEntity entity) {
        if (entity == null) {
            return null;
        }

        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDepartment(entity.getDepartment());
        dto.setSalary(entity.getSalary());

        return dto;
    }

    public EmployeeEntity toEntity(EmployeeDTO dto) {
        if (dto == null) {
            return null;
        }

        EmployeeEntity entity = new EmployeeEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDepartment(dto.getDepartment());
        entity.setSalary(dto.getSalary());

        return entity;
    }
}
