package com.repositories;

import com.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query(value =
            "SELECT *\n" +
            "FROM department\n" +
            "WHERE LOWER(name) LIKE LOWER(:name)", nativeQuery = true)
    Iterable<Department> findByName(String name);

    @Query(value =
            "SELECT *\n" +
            "FROM department\n" +
            "WHERE LOWER(name) LIKE LOWER(:name) AND NOT id = :id", nativeQuery = true)
    Iterable<Department> findByNameAndNotId(long id, String name);

    @Query(value =
            "SELECT *\n" +
            "FROM department\n" +
            "ORDER BY name", nativeQuery = true)
    Iterable<Department> getAll();
}
