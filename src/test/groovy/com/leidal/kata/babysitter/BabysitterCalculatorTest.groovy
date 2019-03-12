package com.leidal.kata.babysitter

import java.time.LocalDateTime

class BabysitterCalculatorTest extends GroovyTestCase {

    BabysitterCalculator babysitterCalculator

    void setUp() {
        babysitterCalculator = new BabysitterCalculator()
    }


    void testShouldNotStartEarlierThan5PM() {
        def msg = shouldFail {
            babysitterCalculator.calculateTotalPay(
                    LocalDateTime.of(2019, 2, 28, 16,00),
                    LocalDateTime.of(2019, 2, 28, 20, 0),
                    new FamilyA()
            )
        }
        assert 'Babysitter cannot start between 4 AM and 5 PM' == msg
    }

    void testShouldNotEndLaterThan4AM() {
        def msg = shouldFail {
            babysitterCalculator.calculateTotalPay(
                    LocalDateTime.of(2019, 2, 28, 17,00),
                    LocalDateTime.of(2019, 3, 1, 5, 00),
                    new FamilyA()
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
                    LocalDateTime.of(2019, 02, 28, 16, 00),
                    new FamilyA()
            )
        }
        assert 'End time must be after start time' == msg
    }

    void testShouldBeAbleToEndAfter4AMIfSameDayAndStartTimeWasBeforeMidnight() {
        BigDecimal pay = babysitterCalculator.calculateTotalPay(
                LocalDateTime.of(2019, 2, 28, 17,00),
                LocalDateTime.of(2019, 2, 28, 22, 00),
                new FamilyA()
        )
        assert pay == BigDecimal.valueOf(75)
    }

    void testShouldNotBeAbleToWorkMoreThan11Hours() {
        def msg = shouldFail {
            babysitterCalculator.calculateTotalPay(
                    LocalDateTime.of(2019, 2, 28, 17,00),
                    LocalDateTime.of(2019, 3, 2, 2, 00),
                    new FamilyA()
            )
        }
        assert 'Babysitter cannot work more than 11 hours' == msg
    }

    void testShouldNotBeAbleToEnterFractionalHours() {
        def msg = shouldFail {
            babysitterCalculator.calculateTotalPay(
                    LocalDateTime.of(2019, 2, 28, 17, 01),
                    LocalDateTime.of(2019, 3, 1, 3, 59),
                    new FamilyA()
            )
        }

        assert 'Babysitter cannot work fractional hours, must start and end on the hour.' == msg
    }

    void testShouldCalculateTotalPayForFamilyA() {
        BigDecimal pay = babysitterCalculator.calculateTotalPay(
                LocalDateTime.of(2019, 2, 28, 17, 00),
                LocalDateTime.of(2019, 3, 1, 4, 00),
                new FamilyA()
        )
        assert pay == BigDecimal.valueOf(190)
    }


}