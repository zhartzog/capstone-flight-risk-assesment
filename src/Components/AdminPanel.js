import React, {Component} from 'react';
import './../stylesheets/AdminPanel.css'
import {Jumbotron, Row, Col, Form, FormGroup, Button} from 'react-bootstrap';

class AdminPanel extends Component{
    
    constructor(props) {
        super(props);

        this.state = ({ maxCrossWinds: 25,
                        maxVisibility: 2,
                        midCrossWinds: 18,
                        midVisibility: 4,
                        setMaxCross: null,
                        setMaxVis: null,
                        setMidCross: null,
                        setMidVis: null});
        
        this.set = this.set.bind(this);
    }

    set(variable, value) {
        if(value != null){
            if(variable === "maxCross")
                this.setState({maxCrossWinds: value});
            if(variable === "midCross")
                this.setState({midCrossWinds: value});
            if(variable === "maxVis")
                this.setState({maxVisibility: value});
            if(variable === "midVis")
                this.setState({midVisibility: value});
        }
    }

    render() {
        return (
            <>
                <Row>
                    <Col>
                        <Jumbotron fluid className="section">
                            <h1>Admin Panel</h1>
                        </Jumbotron>
                    </Col>
                </Row>
                <Form>
                    <Row>
                        <FormGroup as={Col} className="section">
                            <h3>Search for student's submitted forms</h3>
                            <Row>
                                <Form.Label>Student's Name: </Form.Label>
                                <Col><Form.Control type="text"></Form.Control></Col>
                                <Button className="btn btn-default">Search</Button>
                            </Row>
                        </FormGroup>
                        <Col className="section">
                            <h3>Current safety limits</h3>
                            <Row>
                                <Col><h5>Cross Winds (knots)</h5>
                                <p>Max: {this.state.maxCrossWinds}</p>
                                <p>Mid: {this.state.midCrossWinds}</p></Col>
                            </Row>
                            <Row>
                                <Col><h5>Visibility (statue miles)</h5>
                                <p>Max: {this.state.maxVisibility}</p>
                                <p>Mid: {this.state.midVisibility}</p></Col>
                            </Row>
                        </Col>
                        <FormGroup as={Col} className="section">
                            <h3>Set safety limilts</h3>
                            <h5>Cross Winds (knots)</h5>
                            <Row>
                                <Col><Form.Label>Max safe limit: </Form.Label></Col>
                                <Col sm={2}><Form.Control type="number" onChange={e => this.setState({setMaxCross: e.target.value})}></Form.Control></Col>
                                <Col><Button className="btn btn-default" onClick={this.set.bind(this, "maxCross", this.state.setMaxCross)}>Set</Button></Col>
                            </Row>
                            <Row>
                                <Col><Form.Label>Mid safe limit: </Form.Label></Col>
                                <Col sm={2}><Form.Control type="number" onChange={e => this.setState({setMidCross: e.target.value})}></Form.Control></Col>
                                <Col><Button className="btn btn-default" onClick={this.set.bind(this, "midCross", this.state.setMidCross)}>Set</Button></Col>
                            </Row>
                            <h5>Visibility (statue miles)</h5>
                            <Row>
                                <Col className="section"><Form.Label>Max safe limit: </Form.Label></Col>
                                <Col sm={2}><Form.Control type="number" onChange={e => this.setState({setMaxVis: e.target.value})}></Form.Control></Col>
                                <Col><Button className="btn btn-default" onClick={this.set.bind(this, "maxVis", this.state.setMaxVis)}>Set</Button></Col>
                            </Row>
                            <Row>
                                <Col><Form.Label>Mid safe limit: </Form.Label></Col>
                                <Col sm={2}><Form.Control type="number" onChange={e => this.setState({setMidVis: e.target.value})}></Form.Control></Col>
                                <Col><Button className="btn btn-default" onClick={this.set.bind(this, "midVis", this.state.setMidVis)}>Set</Button></Col>
                            </Row>
                        </FormGroup>
                    </Row>
                </Form>
            </>
        );
    }
}

export default AdminPanel;
