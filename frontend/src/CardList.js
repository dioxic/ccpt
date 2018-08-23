import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';

class CardList extends Component {

  constructor(props) {
    super(props);
    this.state = {
      cards: this.props.cards
    };
    this.handleRemove = this.handleRemove.bind(this);
  }

  handleRemove(id) {
    this.props.removeCard(id)
  }

  render() {
    const {cards} = this.state;

    const cardList = cards.map(card => {
      return <tr key={card.id}>
        <td style={{whiteSpace: 'nowrap'}}>{card.name}</td>
        <td>{card.number}</td>
        <td>{card.limit}</td>
        <td>{card.balance}</td>
        <td>
          <ButtonGroup>
            <Button size="sm" color="danger" onClick={() => this.handleRemove(card.id)}>Delete</Button>
          </ButtonGroup>
        </td>
      </tr>
    });

    return (
      <div>
        <Container fluid>
          <h4>Existing Cards</h4>
          <Table className="mt-4">
            <thead>
            <tr>
              <th>Name</th>
              <th>Number</th>
              <th>Limit</th>
              <th>Balance</th>
              <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            {cardList}
            </tbody>
          </Table>
        </Container>
      </div>
    );
  }
}

export default CardList;