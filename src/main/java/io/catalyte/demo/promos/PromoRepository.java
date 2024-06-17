package io.catalyte.demo.promos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Repository interface for managing promotions in the database.
 * Provides methods for accessing and manipulating promotion data.
 */
@Repository
public interface PromoRepository extends JpaRepository<Promo, Integer> {
}
