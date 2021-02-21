import React, { useState } from 'react';
import FlightDutyFormInput from "./FlightDutyFormInput";
import './../stylesheets/RiskAssessmentForm.css';
import {Button, Row, Col, Form, Jumbotron} from 'react-bootstrap';
import DatePicker from 'react-datepicker';

function RiskAssessmentForm() {
    const [departureTime, setDepartureTime] = useState(new Date());
    const [studentName, setStudentName] = useState("");
    const [studentLevel, setStudentLevel] = useState("private");
    const [prevFlights, setPrevFlights] = useState(0);
    const [flightDuty, setFlightDuty] = useState(new Date());
    const [typeOfFlight, setTypeOfFlight] = useState("normal");

    function logState(e) {
        e.preventDefault();
        console.log("departureTime: "+departureTime);
        console.log("Student name: "+studentName);
        console.log("Student level: "+studentLevel);
        console.log("previous flights: "+prevFlights);
        console.log("flightDuty: "+flightDuty);
        console.log("type of flight: "+typeOfFlight);
    }
    return (
        <>
            <Row>
                <Col>
                    <Jumbotron fluid className="md-12">
                        <h1 className="text-center">Risk Assessment Form</h1>
                    </Jumbotron>
                </Col>
            </Row>
            <Form>
                <Form.Row>
                    <Form.Group as={Col} controlId="departureDateAndTime">
                        <Form.Label className="mr-3">Departure Time and Date: </Form.Label>
                        <DatePicker
                            selected={departureTime}
                            onChange={date => setDepartureTime(date)}
                            timeInputLabel="Departure Time:"
                            dateFormat="MM/dd/yyyy h:mm aa"
                            timeFormat="HH:mm"
                            showTimeInput
                            minDate={(new Date())}
                        />
                    </Form.Group>
                </Form.Row>

                <Form.Row>
                    <Form.Group as={Col} controlId="studentName">
                        <Form.Label>Student's Name</Form.Label>
                        <Form.Control
                            type="text"
                            name="student_name"
                            onChange={e => setStudentName(e.target.value)}
                            className=" studentInfo"
                        />
                    </Form.Group>
                </Form.Row>

                <Form.Row>
                    <Form.Group as={Col} controlId="studentLevel">
                        <Form.Label>Certificate pursuing</Form.Label> {/*There is probably a better way to phrase this*/}
                        <Form.Control as="select" name="student_level" onChange={e => setStudentLevel(e.target.value)} value={studentLevel}>
                            <option value="private">Private Pilot's License</option>
                            <option value="instrument">Instrument Rating</option>
                            <option value="commercial">Commercial License</option>
                            <option value="cfi">Certified Flight Instructor</option>
                            <option value="multi">Commercial Multi Engine Add-on</option>
                            <option value="other">Other</option>
                        </Form.Control>
                    </Form.Group>
                </Form.Row>

                <Form.Row>
                    <Form.Group as={Col} controlId="previousFlights">
                        <Form.Label>Previous Flights that day</Form.Label>
                        <Form.Control
                            type="number"
                            name="prevFlights"
                            onChange={e => setPrevFlights(e.target.value)}
                            value={prevFlights}
                        />
                    </Form.Group>
                </Form.Row>

                <FlightDutyFormInput
                    prevFlights={prevFlights}
                    flightDuty={flightDuty}
                    eventHandler={setFlightDuty}
                />

                <Form.Row>
                    <Form.Group as={Col} controlId="typeOfFlight">
                        <Form.Label>Type of Syllabus Flight</Form.Label>
                        <Form.Control as="select" name="student_level" onChange={e => setTypeOfFlight(e.target.value)} value={typeOfFlight}>
                            <option value="normal">Normal</option>
                            <option value="stage_check">Stage Check</option>
                            <option value="checkride">FAA Practical Test</option>
                        </Form.Control>
                    </Form.Group>
                </Form.Row>




                <Button variant="primary" onClick={logState}>
                    Submit
                </Button>
            </Form>

        </>
    );
}


export default RiskAssessmentForm;
