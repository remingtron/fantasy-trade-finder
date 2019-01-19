package com.remingtron.fantasytradefinder.model.espn;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LeagueSettings {
    private Map<Integer, Team> teams;

    public Map<Integer, Team> getTeams() {
        return teams;
    }

    public void setTeams(Map<Integer, Team> teams) {
        this.teams = teams;
    }
}
