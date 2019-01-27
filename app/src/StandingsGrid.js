import React from 'react';
import ReactTable from "react-table";
import 'react-table/react-table.css';

const StandingsGrid = (props) => {
    const columns = [{
      Header: 'First Name',
      accessor: 'firstName'
    }, {
      Header: 'Last Name',
      accessor: 'lastName'
    }, {
      Header: 'Wins',
      accessor: 'wins'
    }, {
      Header: 'Losses',
      accessor: 'losses'
    }, {
      Header: 'Ties',
      accessor: 'ties'
    }, {
      id: 'totalPointsFor',
      Header: 'Points Scored',
      accessor: d => d.totalPointsFor.toFixed(2)
    }, {
      id: 'totalPointsAgainst',
      Header: 'Points Against',
      accessor: d => d.totalPointsAgainst.toFixed(2)
    }, {
      Header: 'Seasons',
      accessor: 'seasons'
    }];

    return (
      <ReactTable data={props.data} columns={columns} defaultSorted={[{id: 'wins', desc: true}]} showPagination={false} pageSize={props.data.length}/>
    );
};

export default StandingsGrid;
