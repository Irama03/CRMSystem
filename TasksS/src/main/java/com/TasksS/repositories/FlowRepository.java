package com.TasksS.repositories;

import com.TasksS.models.Flow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowRepository extends JpaRepository<Flow, Long> {

    @Query(value =
            "SELECT * " +
            "FROM flow " +
            "ORDER by name",nativeQuery = true)
    Iterable<Flow> getAll();

    @Query(value =
            "SELECT *\n" +
            "FROM flow\n" +
            "WHERE LOWER(name) LIKE LOWER(:name)", nativeQuery = true)
    Iterable<Flow> findByName(String name);

    @Query(value =
            "SELECT *\n" +
            "FROM flow\n" +
            "WHERE LOWER(name) LIKE LOWER(:name) AND NOT id = :id", nativeQuery = true)
    Iterable<Flow> findByNameAndNotId(Long id, String name);

}
