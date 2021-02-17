package com.chpok.formula1;

import com.chpok.formula1.formatter.RaceViewFormatter;
import com.chpok.formula1.formatter.RaceViewFormatterImpl;
import com.chpok.formula1.parser.RacersParser;
import com.chpok.formula1.parser.RacersParserImpl;
import com.chpok.formula1.provider.RaceStatisticsProvider;
import com.chpok.formula1.reader.FileReader;
import com.chpok.formula1.reader.FileReaderImpl;
import com.chpok.formula1.sort.RacersSorter;
import com.chpok.formula1.sort.RacersSorterImpl;
import com.chpok.formula1.validation.ValidationProvider;
import com.chpok.formula1.validation.Validator;

public class Formula1ConsoleApplication {
    private static final String START_DATA_FILE_PATH = "/start.log";
    private static final String END_DATA_FILE_PATH = "/end.log";
    private static final String ABBREVIATIONS_DATA_FILE_PATH = "/abbreviations.txt";

    public static void main(String[] args) {
        final ValidationProvider validator = new Validator();
        final FileReader fileReader = new FileReaderImpl();
        final RacersParser parser = new RacersParserImpl();
        final RaceViewFormatter formatter = new RaceViewFormatterImpl();
        final RacersSorter sorter = new RacersSorterImpl();
        
        RaceStatisticsProvider provider = new RaceStatisticsProvider(validator, fileReader, parser, formatter, sorter);
                        
        System.out.print(provider.provideRaceStatistics(START_DATA_FILE_PATH, END_DATA_FILE_PATH, ABBREVIATIONS_DATA_FILE_PATH));
    }
    
}
