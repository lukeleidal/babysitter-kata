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

    void testShouldCalculateTotalPayForFamilyB() {
        BigDecimal pay = babysitterCalculator.calculateTotalPay(
                LocalDateTime.of(2019, 2, 28, 17, 00),
                LocalDateTime.of(2019, 3, 1, 4, 00),
                new FamilyB()
        )
        assert pay == BigDecimal.valueOf(140)
    }

    void testShouldCalculateTotalPayForFamilyC() {
        BigDecimal pay = babysitterCalculator.calculateTotalPay(
                LocalDateTime.of(2019, 2, 28, 17, 00),
                LocalDateTime.of(2019, 3, 1, 4, 00),
                new FamilyC()
        )
        assert pay == BigDecimal.valueOf(189)
    }

    void testPartialPayForFamilyA() {
        BigDecimal pay = babysitterCalculator.calculateTotalPay(
                LocalDateTime.of(2019, 2, 28, 20, 00),
                LocalDateTime.of(2019, 3, 1, 2, 00),
                new FamilyA()
        )
        assert pay == BigDecimal.valueOf(105)
    }

    void testPartialPayForFamilyB() {
        BigDecimal pay = babysitterCalculator.calculateTotalPay(
                LocalDateTime.of(2019, 2, 28, 20, 00),
                LocalDateTime.of(2019, 3, 1, 2, 00),
                new FamilyB()
        )
        assert pay == BigDecimal.valueOf(72)
    }

    void testPartialPayForFamilyC() {
        BigDecimal pay = babysitterCalculator.calculateTotalPay(
                LocalDateTime.of(2019, 2, 28, 20, 00),
                LocalDateTime.of(2019, 3, 1, 2, 00),
                new FamilyC()
        )
        assert pay == BigDecimal.valueOf(96)
    }

    void testMidnightStartPayForFamilyA() {
        BigDecimal pay = babysitterCalculator.calculateTotalPay(
                LocalDateTime.of(2019, 3, 1, 0, 00),
                LocalDateTime.of(2019, 3, 1, 4, 00),
                new FamilyA()
        )
        assert pay == BigDecimal.valueOf(80)
    }

    void testMidnightStartPayForFamilyB() {
        BigDecimal pay = babysitterCalculator.calculateTotalPay(
                LocalDateTime.of(2019, 3, 1, 0, 00),
                LocalDateTime.of(2019, 3, 1, 4, 00),
                new FamilyB()
        )
        assert pay == BigDecimal.valueOf(64)
    }

    void testMidnightStartPayForFamilyC() {
        BigDecimal pay = babysitterCalculator.calculateTotalPay(
                LocalDateTime.of(2019, 3, 1, 0, 00),
                LocalDateTime.of(2019, 3, 1, 4, 00),
                new FamilyC()
        )
        assert pay == BigDecimal.valueOf(60)
    }

    void testMidnightEndPayForFamilyA() {
        BigDecimal pay = babysitterCalculator.calculateTotalPay(
                LocalDateTime.of(2019, 2, 28, 17, 00),
                LocalDateTime.of(2019, 3, 1, 0, 00),
                new FamilyA()
        )
        assert pay == BigDecimal.valueOf(110)
    }

    void testMidnightEndPayForFamilyB() {
        BigDecimal pay = babysitterCalculator.calculateTotalPay(
                LocalDateTime.of(2019, 2, 28, 17, 00),
                LocalDateTime.of(2019, 3, 1, 0, 00),
                new FamilyB()
        )
        assert pay == BigDecimal.valueOf(76)
    }

    void testMidnightEndPayForFamilyC() {
        BigDecimal pay = babysitterCalculator.calculateTotalPay(
                LocalDateTime.of(2019, 2, 28, 17, 00),
                LocalDateTime.of(2019, 3, 1, 0, 00),
                new FamilyC()
        )
        assert pay == BigDecimal.valueOf(129)
    }

    void testStartAndEndBeforeMidnightPayForFamilyA() {
        BigDecimal pay = babysitterCalculator.calculateTotalPay(
                LocalDateTime.of(2019, 2, 28, 17, 00),
                LocalDateTime.of(2019, 2, 28, 23, 00),
                new FamilyA()
        )
        assert pay == BigDecimal.valueOf(90)
    }

    void testStartAndEndBeforeMidnightForFamilyB() {
        BigDecimal pay = babysitterCalculator.calculateTotalPay(
                LocalDateTime.of(2019, 2, 28, 17, 00),
                LocalDateTime.of(2019, 2, 28, 23, 00),
                new FamilyB()
        )
        assert pay == BigDecimal.valueOf(68)
    }

    void testStartAndEndBeforeMidnightPayForFamilyC() {
        BigDecimal pay = babysitterCalculator.calculateTotalPay(
                LocalDateTime.of(2019, 2, 28, 17, 00),
                LocalDateTime.of(2019, 2, 28, 23, 00),
                new FamilyC()
        )
        assert pay == BigDecimal.valueOf(114)
    }

    void testStartAndEndAfterMidnightPayForFamilyA() {
        BigDecimal pay = babysitterCalculator.calculateTotalPay(
                LocalDateTime.of(2019, 3, 1, 1, 00),
                LocalDateTime.of(2019, 3, 1, 4, 00),
                new FamilyA()
        )
        assert pay == BigDecimal.valueOf(60)
    }

    void testStartAndEndAfterMidnightForFamilyB() {
        BigDecimal pay = babysitterCalculator.calculateTotalPay(
                LocalDateTime.of(2019, 3, 1, 1, 00),
                LocalDateTime.of(2019, 3, 1, 4, 00),
                new FamilyB()
        )
        assert pay == BigDecimal.valueOf(48)
    }

    void testStartAndEndAfterMidnightPayForFamilyC() {
        BigDecimal pay = babysitterCalculator.calculateTotalPay(
                LocalDateTime.of(2019, 3, 1, 1, 00),
                LocalDateTime.of(2019, 3, 1, 4, 00),
                new FamilyC()
        )
        assert pay == BigDecimal.valueOf(45)
    }

}