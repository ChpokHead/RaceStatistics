package com.chpok.formula1.formatter;

import java.util.List;
import java.util.stream.IntStream;

import com.chpok.formula1.domain.Racer;

public class RaceViewFormatterImpl implements RaceViewFormatter{
    private static final String FORMAT_STYLE = "%d. %s\t| %-30s| %s%n";
    private static final String DASH = "-";
    private static final String NEW_LINE = "\n";
    private static final int NUM_OF_DASHES = 70;
    private static final int NUM_OF_TOP_RACERS = 15;

    @Override
    public String provideView(List<Racer> racers) {
        StringBuilder builder = new StringBuilder();
        
        IntStream.range(0, racers.size()).forEach(i -> {
            if (i == NUM_OF_TOP_RACERS) {
                builder.append(drawDashes(NUM_OF_DASHES) + NEW_LINE);
            }
            
            builder.append(String.format(FORMAT_STYLE, i + 1, racers.get(i).getName(), racers.get(i).getTeam(), racers.get(i).getBestLapTime()));
        });

        return builder.toString();
    }
    
    private String drawDashes(int numOfDashes) {
        StringBuilder builder = new StringBuilder();
        
        for (; numOfDashes > 0; numOfDashes--) {
            builder.append(DASH);
        }
       
        return builder.toString();
    }
}
