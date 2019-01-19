package com.remingtron.fantasytradefinder.model.espn;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LeagueSettingsResponse {

    private LeagueSettings leaguesettings;

    public LeagueSettings getLeaguesettings() {
        return leaguesettings;
    }

    public void setLeaguesettings(LeagueSettings leaguesettings) {
        this.leaguesettings = leaguesettings;
    }
}
