package com.leidal.kata.babysitter


import java.time.LocalDateTime
import java.time.LocalTime

class BabysitterCalculator {
    BigDecimal calculateTotalPay(LocalDateTime startDateTime, LocalDateTime endDateTime, Family family) throws Exception{
        if(family == null) {
            throw new Exception("Babysitter must have a family for which to babysit")
        } else (
            validateHoursAreAcceptable(startDateTime, endDateTime)
        )

        BigDecimal.ZERO
    }

    void validateHoursAreAcceptable(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        if(endDateTime.isBefore(startDateTime)) {
            throw new Exception("End time must be after start time")
        } else if(startDateTime.toLocalTime().isAfter(LocalTime.of(4,0)) && startDateTime.toLocalTime().isBefore(LocalTime.of(17,00))) {
            throw new Exception("Babysitter cannot start between 4 AM and 5 PM")
        } else if(endDateTime.toLocalTime().isAfter(LocalTime.of(4,00)) && endDateTime.toLocalTime().isBefore(LocalTime.of(17,00))) {
            throw new Exception("Babysitter cannot end between 4 AM and 5 PM")
        } else {
            //do nothing yet, will be paying babysitter
        }
    }
}
