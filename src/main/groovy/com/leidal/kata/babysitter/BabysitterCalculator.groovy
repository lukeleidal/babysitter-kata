package com.leidal.kata.babysitter


import java.time.LocalTime

class BabysitterCalculator {
    BigDecimal calculateTotalPay(LocalTime startTime, LocalTime endTime) throws Exception{
        if(startTime.isBefore(LocalTime.of(17, 00))) {
            throw new Exception("Babysitter cannot start before 5 PM")
        } else {
            throw new Exception("Babysitter cannot end after 4 AM")
        }
    }
}
