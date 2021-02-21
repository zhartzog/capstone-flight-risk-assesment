import React from 'react';
import './../stylesheets/AdminPanel.css'
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
                                <Button>Search</Button>
                            </Row>
                        </FormGroup>
                        <Col className="section">
                            <h3>Current safety limits</h3>
                            <Row>
                                <Col><h5>Cross Winds (knots)</h5>
                                <p>Max: {max.crossWinds}</p>
                                <p>Mid: {mid.crossWinds}</p></Col>
                            </Row>
                            <Row>
                                <Col><h5>Visibility (statue miles)</h5>
                                <p>Max: {max.visibility}</p>
                                <p>Mid: {mid.visibility}</p></Col>
                            </Row>
                        </Col>
                        <FormGroup as={Col} className="section">
                            <h3>Set safety limilts</h3>
                            <h5>Cross Winds (knots)</h5>
                            <Row>
                                <Col><Form.Label>Max safe limit: </Form.Label></Col>
                                <Col sm={2}><Form.Control type="number"></Form.Control></Col>
                                <Col><Button>Set</Button></Col>
                            </Row>
                            <Row>
                                <Col><Form.Label>Mid safe limit: </Form.Label></Col>
                                <Col sm={2}><Form.Control type="number"></Form.Control></Col>
                                <Col><Button>Set</Button></Col>
                            </Row>
                            <h5>Visibility (statue miles)</h5>
                            <Row>
                                <Col><Form.Label>Max safe limit: </Form.Label></Col>
                                <Col sm={2}><Form.Control type="number"></Form.Control></Col>
                                <Col><Button>Set</Button></Col>
                            </Row>
                            <Row>
                                <Col><Form.Label>Mid safe limit: </Form.Label></Col>
                                <Col sm={2}><Form.Control type="number"></Form.Control></Col>
                                <Col><Button>Set</Button></Col>
                            </Row>
                        </FormGroup>
                    </Row>
            </Form>
        </>
    );
}

export default AdminPanel;
