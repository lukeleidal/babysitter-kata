package com.leidal.kata.babysitter

import java.time.Duration
import java.time.LocalDateTime
import java.time.LocalTime

class BabysitterCalculator {
    static BigDecimal calculateTotalPay(LocalDateTime startDateTime, LocalDateTime endDateTime, Family family) throws Exception{
        if(family == null) {
            throw new Exception("Babysitter must have a family for which to babysit")
        }

        validateHoursAreAcceptable(startDateTime, endDateTime)
        Map<Integer, BigDecimal> hoursWorkedMap = getHoursWorkedMap(startDateTime, endDateTime, family)

        BigDecimal pay = BigDecimal.ZERO

        hoursWorkedMap.each { key, value ->
            pay += value
        }

        pay
    }

    static void validateHoursAreAcceptable(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        if(endDateTime.isBefore(startDateTime)) {
            throw new Exception("End time must be after start time")
        } else if(startDateTime.minute != 0 || endDateTime.minute != 0) {
            throw new Exception("Babysitter cannot work fractional hours, must start and end on the hour.")
        } else if(startDateTime.toLocalTime().isAfter(LocalTime.of(4,0)) && startDateTime.toLocalTime().isBefore(LocalTime.of(17,00))) {
            throw new Exception("Babysitter cannot start between 4 AM and 5 PM")
        } else if(endDateTime.toLocalTime().isAfter(LocalTime.of(4,00)) && endDateTime.toLocalTime().isBefore(LocalTime.of(17,00))) {
            throw new Exception("Babysitter cannot end between 4 AM and 5 PM")
        } else if(Duration.between(startDateTime, endDateTime).toHours() > 11) {
            throw new Exception("Babysitter cannot work more than 11 hours")
        }
    }

    static Map<Integer, BigDecimal> getHoursWorkedMap(LocalDateTime startDateTime, LocalDateTime endDateTime, Family family) {
        int startHourIndex = family.payRatesByHour.findIndexOf {it.key == startDateTime.hour}
        //fix - possibly write method to determine endhourindex to use
        int endHourIndex = family.payRatesByHour.findIndexOf {it.key == endDateTime.hour} - 1

        Map<Integer, BigDecimal> hoursWorkedMap = [:]

        family.payRatesByHour.eachWithIndex { it, i ->
            if(i >= startHourIndex && i <= endHourIndex) {
                hoursWorkedMap << it
            }
        }

        hoursWorkedMap
    }
}
