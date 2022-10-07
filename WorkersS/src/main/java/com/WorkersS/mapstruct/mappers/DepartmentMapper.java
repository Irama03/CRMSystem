package com.WorkersS.mapstruct.mappers;

import com.WorkersS.mapstruct.dtos.department.DepartmentGetDto;
import com.WorkersS.mapstruct.dtos.department.DepartmentPostDto;
import com.WorkersS.mapstruct.dtos.department.DepartmentPutDto;
import com.WorkersS.models.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentGetDto departmentToDepartmentGetDto(Department department);

    Iterable<DepartmentGetDto> departmentsToDepartmentsGetDto(Iterable<Department> departments);

    Department departmentPutDtoToDepartment(DepartmentPutDto departmentPutDto);

    Department departmentPostDtoToDepartment(DepartmentPostDto departmentPostDto);
}
