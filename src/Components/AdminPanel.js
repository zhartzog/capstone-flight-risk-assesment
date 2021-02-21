import React, { Component } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Jumbotron, Row, Col, Form, FormGroup, Button} from 'react-bootstrap';

const max = ({crossWinds: 25,
              visibility: 2}),
      mid = ({crossWinds: 18,
              visibility: 4});
    
function AdminPanel() {
    return (
        <>
            <Row>
                <Col>
                    <Jumbotron fluid>
                        <h1>Admin Panel</h1>
                    </Jumbotron>
                </Col>
            </Row>
            <Form>
                    <Row>
                        <Col>
                            <FormGroup>
                                <h3>Search for student's submitted forms</h3>
                                <Row>
                                    <Form.Label>Student's Name: </Form.Label>
                                    <Col><Form.Control type="text"></Form.Control></Col>
                                    <Button>Search</Button>
                                </Row>
                            </FormGroup>
                        </Col>
                        <Col>
                            <h3>Current safety limits</h3>
                            <Row>
                                <Col><p><b>Cross Winds (knots)</b></p>
                                <p>Max: {max.crossWinds}</p>
                                <p>Mid: {mid.crossWinds}</p></Col>
                            </Row>
                            <Row>
                                <Col><p><b>Visibility (statue miles)</b></p>
                                <p>Max: {max.visibility}</p>
                                <p>Mid: {mid.visibility}</p></Col>
                            </Row>
                        </Col>
                        <Col>
                            <FormGroup>
                                <h3>Set safety limilts</h3>
                                <p><b>Cross Winds (knots)</b></p>
                                <Row>
                                    <Form.Label>Max safe limit: </Form.Label>
                                    <Col><Form.Control type="number"></Form.Control></Col>
                                    <Button>Set</Button>
                                </Row>
                                <Row>
                                    <Form.Label>Mid safe limit: </Form.Label>
                                    <Col><Form.Control type="number"></Form.Control></Col>
                                    <Button>Set</Button>
                                </Row>
                                <p><b>Visibility (statue miles)</b></p>
                                <Row>
                                    <Form.Label>Max safe limit: </Form.Label>
                                    <Col><Form.Control type="number"></Form.Control></Col>
                                    <Button>Set</Button>
                                </Row>
                                <Row>
                                    <Form.Label>Mid safe limit: </Form.Label>
                                    <Col><Form.Control type="number"></Form.Control></Col>
                                    <Button>Set</Button>
                                </Row>
                            </FormGroup>
                        </Col>
                    </Row>
            </Form>
        </>
    );
}

export default AdminPanel;
