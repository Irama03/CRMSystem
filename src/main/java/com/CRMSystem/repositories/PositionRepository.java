package com.CRMSystem.repositories;

import com.CRMSystem.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {

    @Query(value =
            "SELECT *\n" +
            "FROM position\n" +
            "WHERE LOWER(name) LIKE LOWER(:name) AND department_id = :departmentId", nativeQuery = true)
    Iterable<Position> findByNameAndDepartment(String name, long departmentId);

    @Query(value =
            "SELECT *\n" +
            "FROM position\n" +
            "WHERE LOWER(name) LIKE LOWER(:name) AND department_id = :departmentId AND NOT id = :id", nativeQuery = true)
    Iterable<Position> findByNameAndDepartmentAndNotId(long id, long departmentId, String name);

    @Query(value =
            "SELECT *\n" +
            "FROM position\n" +
            "ORDER BY name", nativeQuery = true)
    Iterable<Position> getAll();

    @Query(value =
            "SELECT *\n" +
            "FROM position\n" +
            "WHERE department_id = :departmentId\n" +
            "ORDER BY name", nativeQuery = true)
    Iterable<Position> getPositionsOfDepartment(Long departmentId);
}