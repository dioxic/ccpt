import * as React from 'react';
import './App.css';

import logo from './logo.svg';

interface Card {
  id: number;
  name: string;
  number: number;
  limit: number;
  balance: number;
}

interface AppProps {
}

interface AppState {
  cards: Array<Card>;
  isLoading: boolean;
}

class App extends React.Component<AppProps, AppState> {

  constructor(props: AppProps) {
    super(props);

    this.state = {
      cards: [],
      isLoading: false
    };
  }

  render() {
    const {cards, isLoading} = this.state;
  
    if (isLoading) {
      return <p>Loading...</p>;
    }
  
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h1 className="App-title">Welcome to React</h1>
        </header>
        <div>
          <h2>Existing Cards</h2>
          <table className="table table-striped">
              <tr>
                  <th>Name</th>
                  <th>Card Number</th>
                  <th>Balance</th>
                  <th>Limit</th>
              </tr>
              {cards.map((card: Card) =>
                <tr key={card.id}>
                  <td key={card.name}>{card.name}</td>
                  <td key={card.number}>{card.number}</td>
                  <td key={card.balance}>{card.balance}</td>
                  <td key={card.limit}>{card.limit}</td>
                </tr>
              )}              
          </table>
        </div>
      </div>
    );
  }

  componentDidMount() {
    this.setState({isLoading: true});
  
    fetch('http://localhost:8080/api/card')
      .then(response => response.json())
      .then(data => this.setState({cards: data, isLoading: false}));
  }  
}

export default App;
