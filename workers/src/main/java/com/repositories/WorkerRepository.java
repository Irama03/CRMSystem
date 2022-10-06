package com.repositories;

import com.models.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {

    @Query(value =
            "SELECT *\n" +
            "FROM worker\n" +
            "ORDER BY person_surname, person_name", nativeQuery = true)
    Iterable<Worker> getAll();

    @Query(value =
            "SELECT *\n" +
            "FROM (worker INNER JOIN worker_on_position ON id = worker_id)\n" +
            "WHERE position_id = :positionId AND NOT worker_id = :currentWorkerId AND NOT role = 'FIRED'", nativeQuery = true)
    Iterable<Worker> findPrevWorkerOnPosition(Long positionId, Long currentWorkerId);

    @Query(value =
            "SELECT *\n" +
            "FROM (worker INNER JOIN worker_on_position ON id = worker_id)\n" +
                    "INNER JOIN position ON position_id = position.id\n" +
            "WHERE department_id = :departmentId AND is_head = TRUE AND NOT role = 'FIRED'", nativeQuery = true)
    Worker getHeadOfDepartment(Long departmentId);

    @Query(value =
            "SELECT *\n" +
            "FROM (worker INNER JOIN worker_on_position ON id = worker_id)\n" +
            "INNER JOIN position ON position_id = position.id\n" +
            "WHERE department_id = :departmentId AND NOT role = 'FIRED'\n" +
            "ORDER BY person_surname, person_name", nativeQuery = true)
    Iterable<Worker> getWorkersOfDepartment(Long departmentId);

    @Query(value =
            "SELECT *\n" +
            "FROM worker\n" +
            "WHERE LOWER(contacts_email) LIKE LOWER(:email)", nativeQuery = true)
    Iterable<Worker> findByEmail(String email);

    @Query(value =
            "SELECT *\n" +
            "FROM worker\n" +
            "WHERE LOWER(contacts_email) LIKE LOWER(:email) AND NOT id = :id", nativeQuery = true)
    Iterable<Worker> findByEmailAndNotId(long id, String email);
}
