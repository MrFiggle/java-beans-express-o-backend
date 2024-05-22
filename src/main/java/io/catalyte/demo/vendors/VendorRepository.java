package io.catalyte.demo.vendors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Vendor entities.
 * Provides CRUD operations on Vendor entities in the database.
 */
@Repository
public interface VendorRepository extends JpaRepository<Vendor, Integer> {
}
