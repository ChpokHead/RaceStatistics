package com.chpok.formula1.sort;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.chpok.formula1.domain.Racer;

class RacersSorterImplTest {
    private final RacersSorterImpl sorter = new RacersSorterImpl();
    
    @Test
    void sortRacersShouldReturnSortedRacers() {
        final List<Racer> racers = Arrays.asList(new Racer("Daniel Ricciardo", "RED BULL RACING TAG HEUER", "1:12.013"),
                                                   new Racer("Valtteri Bottas", "MERCEDES", "1:12.434"),                                   
                                                   new Racer("Sebastian Vettel", "FERRARI", "1:12.415"),  
                                                   new Racer("Lewis Hamilton", "MERCEDES", "1:12.460"));
        final List<Racer> expected = Arrays.asList(new Racer("Daniel Ricciardo", "RED BULL RACING TAG HEUER", "1:12.013"),
                                                   new Racer("Sebastian Vettel", "FERRARI", "1:12.415"),
                                                   new Racer("Valtteri Bottas", "MERCEDES", "1:12.434"),
                                                   new Racer("Lewis Hamilton", "MERCEDES", "1:12.460"));
        final List<Racer> actual = sorter.sortRacers(racers);
        
        assertThat(actual).isEqualTo(expected);
    }

}
