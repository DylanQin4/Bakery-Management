package itu.s5.bakery.vendeur;

import java.math.BigDecimal;
import java.time.LocalDate;

public record VendeurResponse(
        Vendeur vendeur,
        BigDecimal total,
        LocalDate startDate,
        LocalDate endDate
) {
}
