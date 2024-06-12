package io.catalyte.demo.promos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class responsible for handling HTTP requests related to promotions.
 * Provides endpoints for retrieving, creating, updating, and deleting promotions.
 */
@RestController
@RequestMapping (value = "/promos")
public class PromoController {
    PromoService promoService;

    /**
     * Creates an instance of the controller with the provided instance of the service
     * @param promoService the service to be used by this controller
     */
    @Autowired
    public PromoController( PromoService promoService ) {
        this.promoService = promoService;
    }
    /**
     * Creates a new promotion.
     * @param promoToCreate the promotion to create
     * @return the created promotion
     */
    @PostMapping
    @ResponseStatus( HttpStatus.CREATED )
    public Promo createPromo(@RequestBody Promo promoToCreate ) {
        return promoService.createPromo( promoToCreate );
    }
}
