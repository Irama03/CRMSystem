package com.WorkersS.controllers;

import com.WorkersS.exceptions.department.DepartmentIllegalArgumentException;
import com.WorkersS.exceptions.department.NoDepartmentWithSuchIdException;
import com.WorkersS.exceptions.department.UnableToDeleteDepartmentException;
import com.WorkersS.mapstruct.dtos.department.DepartmentGetDto;
import com.WorkersS.mapstruct.dtos.department.DepartmentPostDto;
import com.WorkersS.mapstruct.dtos.department.DepartmentPutDto;
import com.WorkersS.mapstruct.mappers.DepartmentMapper;
import com.WorkersS.models.Department;
import com.WorkersS.services.implementations.DepartmentServiceImpl;
import com.WorkersS.utils.Utils;
import com.WorkersS.utils.Values;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:" + Values.PORT_OF_FRONTEND})
@RestController
@Validated
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentServiceImpl departmentService;

    @Autowired
    private DepartmentMapper departmentMapper;

    @GetMapping
    public Iterable<DepartmentGetDto> getDepartments(){
        return departmentMapper.departmentsToDepartmentsGetDto(departmentService.getAll());
    }

    @GetMapping("/{id}")
    public DepartmentGetDto getDepartment(@PathVariable("id") Long id) {
        return departmentMapper.departmentToDepartmentGetDto(departmentService.getDepartmentById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable("id") Long id) {
        departmentService.deleteDepartment(id);
    }

    @PostMapping
    public DepartmentGetDto addDepartment(@Valid @RequestBody DepartmentPostDto departmentPostDto){
        return departmentMapper.departmentToDepartmentGetDto(departmentService.addDepartment(departmentMapper.departmentPostDtoToDepartment(departmentPostDto)));
    }

    @PutMapping("/{id}")
    public DepartmentGetDto updateDepartment(@PathVariable("id") Long id, @Valid @RequestBody DepartmentPutDto departmentPutDto) {
        Department department = departmentMapper.departmentPutDtoToDepartment(departmentPutDto);
        department.setId(id);
        return departmentMapper.departmentToDepartmentGetDto(departmentService.updateDepartment(department));
    }

    @ExceptionHandler(NoDepartmentWithSuchIdException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseEntity<Map<String,String>> handleException(NoDepartmentWithSuchIdException e){
        return new ResponseEntity<>(Utils.getExceptionResponse(e),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {DepartmentIllegalArgumentException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String,String>> handleException(DepartmentIllegalArgumentException e){
        return new ResponseEntity<>(Utils.getExceptionResponse(e),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {UnableToDeleteDepartmentException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String,String>> handleException(UnableToDeleteDepartmentException e){
        return new ResponseEntity<>(Utils.getExceptionResponse(e),HttpStatus.BAD_REQUEST);
    }
}
