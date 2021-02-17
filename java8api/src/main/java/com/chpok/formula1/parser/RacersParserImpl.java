package com.chpok.formula1.parser;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import com.chpok.formula1.domain.Racer;

public class RacersParserImpl implements RacersParser {
    private static final String DATE_FORMAT = "yyyy-MM-dd_HH:mm:ss.SSS";
    private static final DateTimeFormatter DATE_FORMATTER  = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private static final String COLUMN = ":";
    private static final String DOT = ".";
    private static final String ZERO = "0";
    private static final String DELIMETER = "_";
    
    @Override
    public List<Racer> parseRacers(List<String> startTimes, List<String> endTimes, List<String> abbreviations) {
        final List<Racer> racers = new ArrayList<>();
        
        IntStream.range(0, abbreviations.size()).forEach(i -> {
            final String abbreviation = abbreviations.get(i).substring(0, 3);
            final LocalDateTime startTime = parseRacerTime(abbreviation, startTimes);
            final LocalDateTime endTime = parseRacerTime(abbreviation, endTimes);
            
            racers.add(new Racer(parseRacerName(abbreviations.get(i)), parseRacerTeam(abbreviations.get(i)), composeRacerBestLapTime(startTime, endTime)));
        });
        
        return racers;
    }
    
    private LocalDateTime parseRacerTime(String racerAbbreviation, List<String> times) {
        int timeNum = 0;
        
        while(timeNum != times.size()) {
            if (times.get(timeNum).startsWith(racerAbbreviation)) {
                return LocalDateTime.parse(times.get(timeNum).substring(racerAbbreviation.length()), DATE_FORMATTER);
            }
            
            timeNum++;
        }
        
        return null;
    }
    
    private String composeRacerBestLapTime(LocalDateTime starTime, LocalDateTime endTime) {
        final Duration timeBetweenStartAndEnd = Duration.between(starTime, endTime);
        final long bestLapMinutes = timeBetweenStartAndEnd.toMinutes();
        final long bestLapSeconds = timeBetweenStartAndEnd.getSeconds() % 60;
        final long bestLapMillis = timeBetweenStartAndEnd.toMillis() - bestLapSeconds * 1000 - bestLapMinutes * 60000;
        
        return bestLapMinutes + COLUMN + bestLapSeconds + DOT + prepareMilliseconds(bestLapMillis);
    }
    
    private String prepareMilliseconds(long bestLapMillis) {
        final String millis = Long.toString(bestLapMillis);
        
        return millis.length() == 2 ? ZERO + millis : millis;
    }
    
    private String parseRacerName(String abbreviation) {
        return abbreviation.split(DELIMETER)[1];
    }
    
    private String parseRacerTeam(String abbreviation) {
        return abbreviation.split(DELIMETER)[2];
    }
    
}
