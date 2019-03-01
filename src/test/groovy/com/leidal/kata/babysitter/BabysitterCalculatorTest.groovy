package com.leidal.kata.babysitter

import java.time.LocalTime

class BabysitterCalculatorTest extends GroovyTestCase {

    BabysitterCalculator babysitterCalculator

    void testShouldNotStartEarlierThan5PM() {
        babysitterCalculator = new BabysitterCalculator()
        def msg = shouldFail {
            babysitterCalculator.calculateTotalPay(LocalTime.of(16, 59), LocalTime.of(23, 59))
        }
        assert 'Babysitter cannot start before 5 PM' == msg
    }

    void testShouldNotEndLaterThan4AM() {
        babysitterCalculator = new BabysitterCalculator()
        def msg = shouldFail {
            babysitterCalculator.calculateTotalPay(LocalTime.of(17,00), LocalTime.of(4, 01))
        }
        assert 'Babysitter cannot end after 4 AM' == msg
    }
}
