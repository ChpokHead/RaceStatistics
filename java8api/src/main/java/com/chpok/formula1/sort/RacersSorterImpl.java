package com.chpok.formula1.sort;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.chpok.formula1.domain.Racer;

public class RacersSorterImpl implements RacersSorter {
    private static final String COLUMN = ":";
    private static final String ZERO = "0";
    private static final String TIME_FORMAT = "HH:mm:ss.SSS";
    private static final DateTimeFormatter TIME_FORMATTER  = DateTimeFormatter.ofPattern(TIME_FORMAT);

    public List<Racer> sortRacers(List<Racer> racers) {
        racers.sort((a,b) ->  parseBestLapTimeToLocalTime(a.getBestLapTime()).compareTo(parseBestLapTimeToLocalTime(b.getBestLapTime())));
        
        return racers;
    }
    
    LocalTime parseBestLapTimeToLocalTime(String bestLapTime) {
        return LocalTime.parse(ZERO + ZERO + COLUMN + ZERO + bestLapTime, TIME_FORMATTER);
    }
    
}
