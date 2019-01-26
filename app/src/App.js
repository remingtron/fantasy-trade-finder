import React, { Component } from 'react';
import './App.css';
import ReactTable from "react-table";
import 'react-table/react-table.css';

class App extends Component {
  render() {
    const data = [{
      name: 'Tanner Linsley',
      age: 26,
      friend: {
        name: 'Jason Maurer',
        age: 23,
      }
    },{
      name: 'Seth Jones',
      age: 29,
      friend: {
        name: 'Zach Werenski',
        age: 21,
      }
    },{
      name: 'Cam Atkinson',
      age: 30,
      friend: {
        name: 'Artemi Panarin',
        age: 27,
      }
    }];

    const columns = [{
      Header: 'Name',
      accessor: 'name'
    }, {
      Header: 'Age',
      accessor: 'age',
      Cell: props => <span className='number'>{props.value}</span>
    }, {
      id: 'friendName',
      Header: 'Friend Name',
      accessor: d => d.friend.name
    }, {
      Header: 'Friend Age',
      accessor: 'friend.age'
    }];

    return (
      <div className="App">
        <header className="App-header">
          <ReactTable data={data} columns={columns} />
        </header>
      </div>
    );
  }
}

export default App;
