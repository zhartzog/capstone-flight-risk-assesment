import React, { useState } from 'react';

import {Container, Row, Col, Form, Jumbotron} from 'react-bootstrap';
import DatePicker from 'react-datepicker';

function RiskAssessmentForm() {
    const [departureTime, setDepartureTime] = useState(new Date());
    const [studentName, setStudentName] = useState();
    return (
        <>
            <Row>
                <Col>
                    <Jumbotron fluid className="md-12">
                        <h1>Risk Assessment Form</h1>
                    </Jumbotron>
                </Col>
            </Row>
            <Form>
                <Form.Group className="mx-3 border-dark">
                    <Col md="12" className="border-dark">
                        <Form.Label>Departure Time and Date: </Form.Label>
                        <DatePicker
                            selected={departureTime}
                            onChange={date => setDepartureTime(date)}
                            timeInputLabel="Departure Time:"
                            dateFormat="MM/dd/yyyy h:mm aa"
                            showTimeInput
                            minDate={(new Date())}
                        />
                    </Col>
                </Form.Group>
                <Form.Group as={Row}>
                    <Form.Label>Student's Name: </Form.Label>
                    <Form.Control
                        type="text"
                        name="student_name"
                        onChange={setStudentName}
                    />
                </Form.Group>
            </Form>
        </>
    );
}

export default RiskAssessmentForm;
