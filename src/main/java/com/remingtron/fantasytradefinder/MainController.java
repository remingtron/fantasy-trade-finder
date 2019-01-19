package com.remingtron.fantasytradefinder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.remingtron.fantasytradefinder.model.espn.LeagueSettingsResponse;
import com.remingtron.fantasytradefinder.model.espn.Owner;
import com.remingtron.fantasytradefinder.model.espn.Record;
import com.remingtron.fantasytradefinder.model.internal.OwnerStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class MainController {

    @Autowired
    PlayerRepository playerRepository;

    @RequestMapping("/main")
    public String index() {
        Player dummyPlayer = new Player();
        dummyPlayer.firstName = "Bob";
        dummyPlayer.lastName = "Jones";
        dummyPlayer.id = UUID.randomUUID().toString();
        dummyPlayer.tradeValue = 17;
        playerRepository.save(dummyPlayer);

        return "Hello world!\n" + "There are " + playerRepository.count() + " players in the database";
    }

    @RequestMapping("/trophy-room")
    public String trophyRoom() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        Map<Integer, OwnerStats> ownerStatsMap = new HashMap<>();

        for (int seasonId = 2011; seasonId <= 2018; seasonId++) {
            LeagueSettingsResponse response = restTemplate.getForObject("http://games.espn.com/ffl/api/v2/leagueSettings?leagueId=113222&seasonId=" + seasonId, LeagueSettingsResponse.class);
            response.getLeaguesettings().getTeams().forEach((key, value) -> {
                Owner owner = value.getOwners().get(0);

                if (!ownerStatsMap.containsKey(owner.getOwnerId())) {
                    OwnerStats ownerStats = new OwnerStats();
                    ownerStats.firstName = owner.getFirstName();
                    ownerStats.lastName = owner.getLastName();
                    ownerStatsMap.put(owner.getOwnerId(), ownerStats);
                }

                OwnerStats ownerStats = ownerStatsMap.get(owner.getOwnerId());
                Record record = value.getRecord();
                ownerStats.wins += record.getOverallWins();
                ownerStats.losses += record.getOverallLosses();
                ownerStats.ties += record.getOverallTies();
                ownerStats.totalPointsScored += record.getPointsFor();
                ownerStats.totalPointsAgainst += record.getPointsAgainst();
                ownerStats.maxPointsScored = Math.max(record.getPointsFor(), ownerStats.maxPointsScored);
                ownerStats.minPointsScored = Math.min(record.getPointsFor(), ownerStats.minPointsScored);
            });
        }

        return new ObjectMapper().writeValueAsString(ownerStatsMap);
    }

}
