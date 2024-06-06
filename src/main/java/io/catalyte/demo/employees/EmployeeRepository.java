package io.catalyte.demo.employees;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Employer entity
 * Provides CRUD operations on Employer entity in the database
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
