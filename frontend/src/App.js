import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import CardList from './CardList';
import CardAdd from './CardAdd';

class App extends Component {
  state = {
    isLoading: true,
    cards: []
  };

  async componentDidMount() {
    const response = await fetch('http://localhost:8080/api/card');
    const body = await response.json();
    this.setState({ cards: body, isLoading: false });
  }

  async addCard(card) {
    let cards = this.state.cards

    const response = await fetch('http://localhost:8080/api/card', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(card),
    })

    const body = await response.json();
    cards.push(body);
    this.setState({cards: cards});
  }

  // something is wrong here - the card gets deleted but the list is not updated
  async removeCard(id) {
    await fetch(`http://localhost:8080/api/card/${id}`, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => {
      let updatedcards = [...this.state.cards].filter(i => i.id !== id);
      this.setState({cards: updatedcards});
      console.log(updatedcards)
    });
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
          <h1 className="App-title">Credit Card System</h1>
        </header>
        <CardAdd addCard={this.addCard.bind(this)}/>
        <CardList cards={cards} removeCard={this.removeCard.bind(this)}/>
      </div>
    );
  }
}

export default App;
