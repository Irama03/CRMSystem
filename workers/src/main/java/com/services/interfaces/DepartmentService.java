package com.services.interfaces;

import com.models.Department;

public interface DepartmentService {

    Department addDepartment(String name);
    Department addDepartment(Department department);

    boolean departmentExistsById(Long id);

    boolean deleteDepartment(Long id); // throws DepartmentNotFoundException;
    Department updateDepartment(Long id, String name);
    Department updateDepartment(Department department);
    Department updateDepartmentNoCheck(Department department);
    Department getDepartmentById(Long id); // throws DepartmentNotFoundException;

    Iterable<Department> getAll();
}
