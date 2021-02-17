package com.chpok.formula1.parser;

import java.util.List;

import com.chpok.formula1.domain.Racer;

public interface RacersParser {
    List<Racer> parseRacers(List<String> startTimes, List<String> endTimes, List<String> abbreviations);
}
