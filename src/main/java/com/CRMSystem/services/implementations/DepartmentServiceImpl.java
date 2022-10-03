package com.CRMSystem.services.implementations;

import com.CRMSystem.exceptions.department.DepartmentIllegalArgumentException;
import com.CRMSystem.exceptions.department.NoDepartmentWithSuchIdException;
import com.CRMSystem.exceptions.department.UnableToDeleteDepartmentException;
import com.CRMSystem.models.Department;
import com.CRMSystem.repositories.DepartmentRepository;
import com.CRMSystem.services.interfaces.DepartmentService;
import com.CRMSystem.utils.Utils;
import com.CRMSystem.utils.Values;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private Utils utils;

    @Override
    public Department addDepartment(String name) {
        name = utils.processString(name);
        utils.checkName(name);
        Iterable<Department> departmentWithSuchName = departmentRepository.findByName(name);
        if (departmentWithSuchName.iterator().hasNext())
            throw new DepartmentIllegalArgumentException(Values.DEPARTMENT_WITH_SUCH_NAME_ALREADY_EXISTS);
        return departmentRepository.save(new Department(name));
    }

    @Override
    public Department addDepartment(Department department) {
        department.setId(-1L);
        return addDepartment(department.getName());
    }

    @Override
    public boolean departmentExistsById(Long id) {
        return departmentRepository.existsById(id);
    }

    @Override
    public boolean deleteDepartment(Long id) {
        if(!departmentExistsById(id)) throw new NoDepartmentWithSuchIdException(id);
        try {
            departmentRepository.deleteById(id);
        }
        catch (Exception e) {
            throw new UnableToDeleteDepartmentException();
        }
        return true;
    }

    @Override
    public Department updateDepartment(Long id, String name) {
        name = utils.processString(name);
        utils.checkName(name);

        Iterable<Department> departmentsWithSuchName = departmentRepository.findByNameAndNotId(id, name);
        if (departmentsWithSuchName.iterator().hasNext())
            throw new DepartmentIllegalArgumentException(Values.DEPARTMENT_WITH_SUCH_NAME_ALREADY_EXISTS);

        Department department = departmentRepository.findById(id).orElseThrow(() -> new NoDepartmentWithSuchIdException(id));
        if (nothingChanged(department, name))
            return department;
        department.setName(name);
        return departmentRepository.save(department);
    }

    private boolean nothingChanged(Department department, String name) {
        return department.getName().equals(name);
    }

    @Override
    public Department updateDepartment(Department department) {
        return updateDepartment(department.getId(), department.getName());
    }

    @Override
    public Department updateDepartmentNoCheck(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new NoDepartmentWithSuchIdException(id));
    }

    @Override
    public Iterable<Department> getAll() {
        return departmentRepository.getAll();
    }
}
