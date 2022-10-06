package com.mapstruct.mappers;

import com.mapstruct.dtos.department.DepartmentGetDto;
import com.mapstruct.dtos.department.DepartmentPostDto;
import com.mapstruct.dtos.department.DepartmentPutDto;
import com.models.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentGetDto departmentToDepartmentGetDto(Department department);

    Iterable<DepartmentGetDto> departmentsToDepartmentsGetDto(Iterable<Department> departments);

    Department departmentPutDtoToDepartment(DepartmentPutDto departmentPutDto);

    Department departmentPostDtoToDepartment(DepartmentPostDto departmentPostDto);
}
