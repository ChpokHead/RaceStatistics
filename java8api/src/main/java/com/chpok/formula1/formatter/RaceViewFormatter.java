package com.chpok.formula1.formatter;

import java.util.List;
import com.chpok.formula1.domain.Racer;

public interface RaceViewFormatter {
    String provideView(List<Racer> racers);
}
