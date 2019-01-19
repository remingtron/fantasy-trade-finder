package com.remingtron.fantasytradefinder.model.espn;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Record {
    private int overallWins;
    private int overallLosses;
    private int overallTies;
    private float pointsAgainst;
    private float pointsFor;

    public int getOverallWins() {
        return overallWins;
    }

    public void setOverallWins(int overallWins) {
        this.overallWins = overallWins;
    }

    public int getOverallLosses() {
        return overallLosses;
    }

    public void setOverallLosses(int overallLosses) {
        this.overallLosses = overallLosses;
    }

    public int getOverallTies() {
        return overallTies;
    }

    public void setOverallTies(int overallTies) {
        this.overallTies = overallTies;
    }

    public float getPointsAgainst() {
        return pointsAgainst;
    }

    public void setPointsAgainst(float pointsAgainst) {
        this.pointsAgainst = pointsAgainst;
    }

    public float getPointsFor() {
        return pointsFor;
    }

    public void setPointsFor(float pointsFor) {
        this.pointsFor = pointsFor;
    }
}
