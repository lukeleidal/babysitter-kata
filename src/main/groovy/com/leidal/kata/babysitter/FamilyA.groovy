package com.leidal.kata.babysitter


class FamilyA implements Family {
    Map<Integer, BigDecimal> getPayRatesByHour() {
        [
                17: BigDecimal.valueOf(15),
                18: BigDecimal.valueOf(15),
                19: BigDecimal.valueOf(15),
                20: BigDecimal.valueOf(15),
                21: BigDecimal.valueOf(15),
                22: BigDecimal.valueOf(15),
                23: BigDecimal.valueOf(20),
                0 : BigDecimal.valueOf(20),
                1 : BigDecimal.valueOf(20),
                2 : BigDecimal.valueOf(20),
                3 : BigDecimal.valueOf(20)
        ]
    }
}
