package com.leidal.kata.babysitter


class FamilyB implements Family {
    Map<Integer, BigDecimal> getPayRatesByHour() {
        [
                17: BigDecimal.valueOf(12),
                18: BigDecimal.valueOf(12),
                19: BigDecimal.valueOf(12),
                20: BigDecimal.valueOf(12),
                21: BigDecimal.valueOf(12),
                22: BigDecimal.valueOf(8),
                23: BigDecimal.valueOf(8),
                0 : BigDecimal.valueOf(16),
                1 : BigDecimal.valueOf(16),
                2 : BigDecimal.valueOf(16),
                3 : BigDecimal.valueOf(16),
                4 : BigDecimal.valueOf(0)
        ]
    }
}
