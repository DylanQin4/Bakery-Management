package itu.s5.bakery.vendeur;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record CommissionGenre(
        Genre genre,
        BigDecimal totalCommission
) {
    public BigDecimal getTotalCommission(){
        if (totalCommission == null) {
            return BigDecimal.ZERO;
        }
        return totalCommission.setScale(2, RoundingMode.HALF_UP);
    }
}
