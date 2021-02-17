package com.chpok.formula1.provider;

import java.util.List;
import com.chpok.formula1.domain.Racer;
import com.chpok.formula1.formatter.RaceViewFormatter;
import com.chpok.formula1.parser.RacersParser;
import com.chpok.formula1.reader.FileReader;
import com.chpok.formula1.sort.RacersSorter;
import com.chpok.formula1.validation.ValidationProvider;

public class RaceStatisticsProvider {
    private final ValidationProvider validator;
    private final FileReader fileReader;
    private final RacersParser parser;
    private final RaceViewFormatter formatter;
    private final RacersSorter sorter;
    
    public RaceStatisticsProvider(ValidationProvider validator, FileReader fileReader, RacersParser parser,
            RaceViewFormatter formatter, RacersSorter sorter) {
        this.validator = validator;
        this.fileReader = fileReader;
        this.parser = parser;
        this.formatter = formatter;
        this.sorter = sorter;
    }
    
    public String provideRaceStatistics(String startDataFilename, String endDataFilename, String abbreviationsFilename) {
        validator.validate(startDataFilename, endDataFilename, abbreviationsFilename);
        
        final List<String> startTimes = fileReader.readFile(startDataFilename);
        final List<String> endTimes = fileReader.readFile(endDataFilename);
        final List<String> abbreviations = fileReader.readFile(abbreviationsFilename);

        final List<Racer> racers = parser.parseRacers(startTimes, endTimes, abbreviations);
        
        return formatter.provideView(sorter.sortRacers(racers));
    }
}
