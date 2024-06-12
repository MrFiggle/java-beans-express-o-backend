package io.catalyte.demo.promos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Implementation of the PromoService interface.
 * Provides functionality related to managing promotions.
 */
@Service
public class PromoServiceImpl implements PromoService{
    PromoRepository promoRepository;

    /**
     * Creates an instance of the service with the provided instance of the repository
     * @param promoRepository the repository to be used by this service
     */
    @Autowired
    public PromoServiceImpl(PromoRepository promoRepository) {
        this.promoRepository = promoRepository;
    }
    /**
     * Creates a new promotion.
     * @param promoToCreate the promotion to create
     * @return the created promotion
     */
    @Override
    public Promo createPromo(Promo promoToCreate) {
        return promoRepository.save(promoToCreate) ;
    }
}
