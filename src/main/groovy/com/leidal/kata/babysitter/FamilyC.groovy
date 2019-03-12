package com.leidal.kata.babysitter


class FamilyC implements Family {
    Map<Integer, BigDecimal> getPayRatesByHour() {
        [
                17: BigDecimal.valueOf(21),
                18: BigDecimal.valueOf(21),
                19: BigDecimal.valueOf(21),
                20: BigDecimal.valueOf(21),
                21: BigDecimal.valueOf(15),
                22: BigDecimal.valueOf(15),
                23: BigDecimal.valueOf(15),
                0 : BigDecimal.valueOf(15),
                1 : BigDecimal.valueOf(15),
                2 : BigDecimal.valueOf(15),
                3 : BigDecimal.valueOf(15)
        ]
    }
}
