package io.catalyte.demo.promos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PromoServiceImplTest {
    PromoService promoService;
    Promo testPromo;
    @Mock
    PromoRepository promoRepository;
    @BeforeEach
    public void setUp() {
        promoService = new PromoServiceImpl(promoRepository);
        testPromo = new Promo("testCode","testDescription","testCampaignName",BigDecimal.valueOf(1),PromoType.FLAT,"01/01/2001","01/02/2001",BigDecimal.valueOf(0));
    }
    @Test
    public void createPromo_withValidPromo_returnsPersistedPromo(){
        when(promoRepository.save(any(Promo.class))).thenReturn(testPromo);
        Promo results = promoService.createPromo(testPromo);
        assertEquals(testPromo, results);
    }
}
