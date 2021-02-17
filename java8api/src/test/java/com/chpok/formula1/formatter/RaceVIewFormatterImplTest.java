package com.chpok.formula1.formatter;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.chpok.formula1.domain.Racer;

class RaceVIewFormatterImplTest {
    private final RaceViewFormatterImpl formatter = new RaceViewFormatterImpl();
    
    @Test
    void provideViewShouldReturnCorrectViewWhenRacersListCorrect() {
        final List<Racer> racers = Arrays.asList(new Racer("Daniel Ricciardo", "RED BULL RACING TAG HEUER", "1:12.013"),
                                                   new Racer("Sebastian Vettel", "FERRARI", "1:12.415"), 
                                                   new Racer("Valtteri Bottas", "MERCEDES", "1:12.434"), 
                                                   new Racer("Lewis Hamilton", "MERCEDES", "1:12.460"));
        
        final String expected = "1. Daniel Ricciardo\t| RED BULL RACING TAG HEUER     | 1:12.013\n2. Sebastian Vettel\t| FERRARI                       | 1:12.415\n3. Valtteri Bottas\t| MERCEDES                      | 1:12.434\n4. Lewis Hamilton\t| MERCEDES                      | 1:12.460\n";
        final String actual = formatter.provideView(racers);
        
        assertThat(actual).isEqualTo(expected);
    }

}
