package com.CRMSystem.mapstruct.mappers;

import com.CRMSystem.mapstruct.dtos.department.DepartmentGetDto;
import com.CRMSystem.mapstruct.dtos.department.DepartmentPostDto;
import com.CRMSystem.mapstruct.dtos.department.DepartmentPutDto;
import com.CRMSystem.models.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentGetDto departmentToDepartmentGetDto(Department department);

    Iterable<DepartmentGetDto> departmentsToDepartmentsGetDto(Iterable<Department> departments);

    Department departmentPutDtoToDepartment(DepartmentPutDto departmentPutDto);

    Department departmentPostDtoToDepartment(DepartmentPostDto departmentPostDto);
}
