package com.leidal.kata.babysitter

import java.time.LocalTime

class BabysitterCalculatorTest extends GroovyTestCase {

    BabysitterCalculator babysitterCalculator

    void testShouldNotStartEarlierThan5PM() {
        babysitterCalculator = new BabysitterCalculator()
        def msg = shouldFail {
            BigDecimal pay = babysitterCalculator.calculateTotalPay(LocalTime.of(16, 59))
        }
        assert 'Babysitter cannot start before 5 PM' == msg
    }
}
