package com.leidal.kata.babysitter


import java.time.LocalDateTime
import java.time.LocalTime

class BabysitterCalculator {
    BigDecimal calculateTotalPay(LocalDateTime startDateTime, LocalDateTime endDateTime, Family family) throws Exception{
        if(startDateTime.toLocalTime().isBefore(LocalTime.of(17,00))) {
            throw new Exception("Babysitter cannot start before 5 PM")
        } else if(endDateTime.toLocalTime().isAfter(LocalTime.of(4,00))){
            throw new Exception("Babysitter cannot end after 4 AM")
        } else {
            throw new Exception("Babysitter must have a family for which to babysit")
        }
    }
}
