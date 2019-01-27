import React, { Component } from 'react';
import StandingsGrid from './StandingsGrid.js';
import axios from 'axios';
import { pick } from 'lodash';

export default class StandingsGridContainer extends Component {

  state = {
    data: []
  };

  retrieveSettings(year) {
    return axios.get(`http://games.espn.com/ffl/api/v2/leagueSettings?leagueId=113222&seasonId=${year}`);
  }

  parseOwnerData(leagueSettings) {
    return Object.values(leagueSettings.teams).map(team => {
      var owner = team.owners[0];
      var record = team.record;
      return {
        ownerId: owner.ownerId,
        firstName: owner.firstName,
        lastName: owner.lastName,
        wins: record.overallWins,
        losses: record.overallLosses,
        ties: record.overallTies,
        pointsFor: record.pointsFor,
        pointsAgainst: record.pointsAgainst
      };
    });
  }

  combineSeasonData(accumulator, currentValue) {
    accumulator[currentValue.ownerId] = accumulator[currentValue.ownerId] || this.createNewEntry(currentValue);

    accumulator[currentValue.ownerId].wins += currentValue.wins;
    accumulator[currentValue.ownerId].losses += currentValue.losses;
    accumulator[currentValue.ownerId].ties += currentValue.ties;
    accumulator[currentValue.ownerId].totalPointsFor += currentValue.pointsFor;
    accumulator[currentValue.ownerId].totalPointsAgainst += currentValue.pointsAgainst;
    accumulator[currentValue.ownerId].seasons += 1;

    return accumulator;
  }

  createNewEntry(value) {
    var ownerEntry = pick(value, ['ownerId', 'firstName', 'lastName']);
    return {
      ...ownerEntry,
      wins: 0,
      losses: 0,
      ties: 0,
      totalPointsFor: 0,
      totalPointsAgainst: 0,
      seasons: 0
    }
  }

  componentDidMount() {
    var years = [2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018];

    axios.all(years.map(year => this.retrieveSettings(year)))
      .then(results => {
        var ownerData = results.flatMap(result => this.parseOwnerData(result.data.leaguesettings)).reduce((a, v) => this.combineSeasonData(a, v), {});
        console.log(ownerData);
        this.setState({data: Object.values(ownerData)});
      });
  }

  render() {
    return (
      <StandingsGrid data={this.state.data} />
    );
  }
}
