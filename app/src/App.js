import React, { Component } from 'react';
import './App.css';
import StandingsGridContainer from './StandingsGridContainer.js';
import 'react-table/react-table.css';

export default class App extends Component {
  render() {
    return (
      <div className="App">
        <header className="App-header">
          <StandingsGridContainer />
        </header>
      </div>
    );
  }
}
