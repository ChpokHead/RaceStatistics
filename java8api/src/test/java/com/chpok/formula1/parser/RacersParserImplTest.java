package com.chpok.formula1.parser;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.chpok.formula1.domain.Racer;

class RacersParserImplTest {
    private final RacersParserImpl parser = new RacersParserImpl();
    
    @Test
    void parseRacersShouldReturnCorrectRacersIfFileLinesAreCorrect() {
        final List<Racer> expected = Arrays.asList(new Racer("Daniel Ricciardo", "RED BULL RACING TAG HEUER", "1:12.013"),
                                                   new Racer("Sebastian Vettel", "FERRARI", "1:12.415"),
                                                   new Racer("Lewis Hamilton", "MERCEDES", "1:12.460"),
                                                   new Racer("Valtteri Bottas", "MERCEDES", "1:12.434"));
                
        final List<String> abbreviations = Arrays.asList("DRR_Daniel Ricciardo_RED BULL RACING TAG HEUER",
                "SVF_Sebastian Vettel_FERRARI", 
                "LHM_Lewis Hamilton_MERCEDES", 
                "VBM_Valtteri Bottas_MERCEDES");
        final List<String> startTimes = Arrays.asList("DRR2018-05-24_12:14:12.054",
                "SVF2018-05-24_12:02:52.917", 
                "LHM2018-05-24_12:18:20.125", 
                "VBM2018-05-24_12:00:00.000");
        final List<String> endTimes = Arrays.asList("DRR2018-05-24_12:15:24.067",
                "SVF2018-05-24_12:04:05.332", 
                "LHM2018-05-24_12:19:32.585", 
                "VBM2018-05-24_12:01:12.434");
        
        final List<Racer> actual = parser.parseRacers(startTimes, endTimes, abbreviations);
        
        assertThat(actual).isEqualTo(expected);
    }

}
