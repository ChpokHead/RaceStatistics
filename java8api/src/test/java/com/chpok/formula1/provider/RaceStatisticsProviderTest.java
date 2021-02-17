package com.chpok.formula1.provider;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.chpok.formula1.domain.Racer;
import com.chpok.formula1.formatter.RaceViewFormatter;
import com.chpok.formula1.parser.RacersParser;
import com.chpok.formula1.reader.FileReader;
import com.chpok.formula1.sort.RacersSorter;
import com.chpok.formula1.validation.ValidationProvider;

@ExtendWith(MockitoExtension.class)
class RaceStatisticsProviderTest {
    @Mock
    private ValidationProvider validator;
    @Mock
    private FileReader fileReader;
    @Mock
    private RacersParser parser;
    @Mock
    private RaceViewFormatter formatter;
    @Mock
    private RacersSorter sorter;
    @InjectMocks
    private RaceStatisticsProvider provider;
    
    @Test
    void provideRaceStatisticsShouldReturnCorrectRaceStatisticsIfAllFilesAreCorrect() {
        final String startFilename = "/start.log";
        final String endFilename = "/end.log";
        final String abbreviationsFilename = "/abbreviations.txt";
        final String expected = "1. Daniel Ricciardo\t| RED BULL RACING TAG HEUER     | 1:12.013\n2. Sebastian Vettel\t| FERRARI                       | 1:12.415\n3. Valtteri Bottas\t| MERCEDES                      | 1:12.434\n4. Lewis Hamilton\t| MERCEDES                      | 1:12.460\n";

        final List<String> starts = Arrays.asList("DRR2018-05-24_12:14:12.054",
                "SVF2018-05-24_12:02:52.917", 
                "LHM2018-05-24_12:18:20.125", 
                "VBM2018-05-24_12:00:00.000");
        final List<String> ends = Arrays.asList("DRR2018-05-24_12:15:24.067",
                "SVF2018-05-24_12:04:03.332", 
                "LHM2018-05-24_12:19:32.585", 
                "VBM2018-05-24_12:01:12.434");
        final List<String> abbreviations = Arrays.asList("DRR_Daniel Ricciardo_RED BULL RACING TAG HEUER",
                "SVF_Sebastian Vettel_FERRARI", 
                "LHM_Lewis Hamilton_MERCEDES", 
                "VBM_Valtteri Bottas_MERCEDES");
        final List<Racer> racers = Arrays.asList(new Racer("Daniel Ricciardo", "RED BULL RACING TAG HEUER", "1:12.013"),
                new Racer("Sebastian Vettel", "FERRARI", "1:12.415"),
                new Racer("Lewis Hamilton", "MERCEDES", "1:12.460"),
                new Racer("Valtteri Bottas", "MERCEDES", "1:12.434"));
        final List<Racer> sortedRacers = Arrays.asList(new Racer("Daniel Ricciardo", "RED BULL RACING TAG HEUER", "1:12.013"),
                new Racer("Sebastian Vettel", "FERRARI", "1:12.415"),
                new Racer("Valtteri Bottas", "MERCEDES", "1:12.434"),
                new Racer("Lewis Hamilton", "MERCEDES", "1:12.460"));
        
        doNothing().when(validator).validate(startFilename, endFilename, abbreviationsFilename);
        
        when(fileReader.readFile(startFilename)).thenReturn(starts);
        when(fileReader.readFile(endFilename)).thenReturn(ends);
        when(fileReader.readFile(abbreviationsFilename)).thenReturn(abbreviations);
        
        when(parser.parseRacers(starts, ends, abbreviations)).thenReturn(racers);
        
        when(sorter.sortRacers(racers)).thenReturn(sortedRacers);
        
        when(formatter.provideView(sortedRacers)).thenReturn(expected);
        
        final String actual = provider.provideRaceStatistics(startFilename, endFilename, abbreviationsFilename);
        
        assertThat(actual).isEqualTo(expected);
    }
    
    @Test
    void provideRaceStatisticsShouldThrowIllegalArgumentExceptionIfFilesAreIncorrect() {
        final String startFilename = "";
        final String endFilename = "/end.log";
        final String abbreviationsFilename = "/abbreviations.txt";
        final String expected = "Start data file path is empty!";
        
        doThrow(new IllegalArgumentException(expected)).when(validator).validate(startFilename, endFilename, abbreviationsFilename);
        
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> validator.validate(startFilename, endFilename, abbreviationsFilename)).withMessage(expected);
    }

}
