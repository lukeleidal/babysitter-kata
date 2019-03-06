package com.leidal.kata.babysitter

import java.time.LocalDateTime
import java.time.LocalTime

class BabysitterCalculatorTest extends GroovyTestCase {

    BabysitterCalculator babysitterCalculator

    void setUp() {
        babysitterCalculator = new BabysitterCalculator()
    }


    void testShouldNotStartEarlierThan5PM() {
        def msg = shouldFail {
            babysitterCalculator.calculateTotalPay(
                    LocalDateTime.of(2019, 2, 28, 16, 59),
                    LocalDateTime.of(2019, 2, 28, 20, 0),
                    new Family()
            )
        }
        assert 'Babysitter cannot start between 4 AM and 5 PM' == msg
    }

    void testShouldNotEndLaterThan4AM() {
        def msg = shouldFail {
            babysitterCalculator.calculateTotalPay(
                    LocalDateTime.of(2019, 2, 28, 17,00),
                    LocalDateTime.of(2019, 3, 1, 4, 01),
                    new Family()
            )
        }
        assert 'Babysitter cannot end between 4 AM and 5 PM' == msg
    }

    void testShouldBabysitForOneFamily() {
        def msg = shouldFail {
            babysitterCalculator.calculateTotalPay(
                    LocalDateTime.of(2019, 02, 28, 17,00),
                    LocalDateTime.of(2019, 02, 28, 00,00),
                    null)
        }
        assert 'Babysitter must have a family for which to babysit' == msg
    }

    void testShouldNotHaveEndTimeBeforeStartTime() {
        def msg = shouldFail {
            babysitterCalculator.calculateTotalPay(
                    LocalDateTime.of(2019, 02, 28, 17, 00),
                    LocalDateTime.of(2019, 02, 28, 16, 59),
                    new Family()
            )
        }
        assert 'End time must be after start time' == msg
    }

    void testShouldBeAbleToEndAfter4AMIfSameDayAndStartTimeWasBeforeMidnight() {
        BigDecimal pay = babysitterCalculator.calculateTotalPay(
                LocalDateTime.of(2019, 2, 28, 17,00),
                LocalDateTime.of(2019, 2, 28, 22, 00),
                new Family()
        )
        assert pay == 0
    }
}