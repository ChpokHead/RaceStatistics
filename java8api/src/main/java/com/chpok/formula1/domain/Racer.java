package com.chpok.formula1.domain;

import java.util.Objects;

public class Racer {
    private final String name;
    private final String team;
    private final String bestLapTime;
    
    public Racer(String name, String team, String bestLapTime) {
        this.name = name;
        this.team = team;
        this.bestLapTime = bestLapTime;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public String getBestLapTime() {
        return bestLapTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bestLapTime, name, team);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
            
        Racer other = (Racer) obj;
        
        return Objects.equals(bestLapTime, other.bestLapTime) && Objects.equals(name, other.name)
                && Objects.equals(team, other.team);
    }
    
    
}
