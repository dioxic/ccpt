import React, { Component } from 'react';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';

class CardAdd extends Component {

  emptyItem = {
    name: 'Shabby',
    number: 69,
    limit: 1000,
    balance: 0
  };

  constructor(props) {
    super(props);
    this.state = {
      item: this.emptyItem
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleSubmit(event) {
    event.preventDefault();
    this.props.addCard(this.state.item)
  }

  handleChange(event) {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    let item = {...this.state.item};
    item[name] = value;
    this.setState({item});
  }

  render() {
    const {item} = this.state;

    return <div>
      <Container>
        <h4>Add</h4>
        <Form onSubmit={this.handleSubmit}>
          <FormGroup>
            <Label for="name">Name</Label>
            <Input type="text" name="name" id="name" value={item.name || ''}
                   onChange={this.handleChange} />
          </FormGroup>
          <FormGroup>
            <Label for="number">Card Number</Label>
            <Input type="text" name="number" id="number" value={item.number || ''}
                   onChange={this.handleChange} />
          </FormGroup>
          <FormGroup>
            <Label for="limit">Limit</Label>
            <Input type="number" name="limit" id="limit" value={item.limit || ''}
                   onChange={this.handleChange} />
          </FormGroup>
          <FormGroup>
            <Button color="primary" type="submit">Save</Button>{' '}
          </FormGroup>
        </Form>
      </Container>
    </div>
  }
}

export default CardAdd;