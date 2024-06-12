package io.catalyte.demo.promos;

/**
 * Service interface for managing promotions.
 * Defines methods for retrieving, creating, updating, and deleting promotions.
 */
public interface PromoService {
    Promo createPromo(Promo promoToCreate);
}
