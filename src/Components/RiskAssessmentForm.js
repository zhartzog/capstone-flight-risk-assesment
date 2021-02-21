import React, { useState } from 'react';
import FlightDutyFormInput from "./FlightDutyFormInput";
import TypeOfFlightFormInput from "./TypeOfFlightFormInput";
import './../stylesheets/RiskAssessmentForm.css';
import {Button, Row, Col, Form, Jumbotron} from 'react-bootstrap';
import DatePicker from 'react-datepicker';

function RiskAssessmentForm() {
    const [departureTime, setDepartureTime] = useState(new Date());
    const [departureAirport, setDepartureAirport] = useState();
    const [studentName, setStudentName] = useState("");
    const [studentLevel, setStudentLevel] = useState("private");
    const [isDualFlight, setIsDualFlight] = useState(false);
    const [prevFlights, setPrevFlights] = useState(0);
    const [flightDuty, setFlightDuty] = useState(new Date());
    const [categoryOfFlight, setCategoryOfFlight] = useState("normal");
    const [typeOfFlight, setTypeOfFlight] = useState("local_flight");
    const [xcDestination, setXcDestination] = useState("");

    function logState(e) {
        /* Remember that setState() is async so console.log my lag behind the state change*/
        e.preventDefault();
        console.log("-----FlightAssessmentForm State Variables-----")
        console.log("departureTime: "+departureTime);
        console.log("Departure Airport: "+departureAirport);
        console.log("Student name: "+studentName);
        console.log("Student level: "+studentLevel);
        console.log("Is Dual flight: "+isDualFlight);
        console.log("previous flights: "+prevFlights);
        console.log("flightDuty: "+flightDuty);
        console.log("Category of flight: "+categoryOfFlight);
        console.log("Type of Flight: "+typeOfFlight);
        console.log("---------------------------------------------")
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
                    <Form.Group as={Col} controlId="departureAirport">
                        <Form.Label>Departure Airport</Form.Label>
                        <Form.Control
                            type="text"
                            name="departure_airport"
                            onChange={e => setDepartureAirport(e.target.value)}
                            className="departureAirport"
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
                    <Form.Group id="dualFlight" as={Col}>
                        <Form.Check
                            type="checkbox" label="This is a dual flight"
                            onChange={e => setIsDualFlight(e.target.value === "on")}
                            value={isDualFlight}
                        />
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
                    <Form.Group as={Col} controlId="catOfFlight">
                        <Form.Label>Category of Syllabus Flight</Form.Label>
                        <Form.Control as="select" name="student_level" onChange={e => setCategoryOfFlight(e.target.value)} value={categoryOfFlight}>
                            <option value="normal">Normal</option>
                            <option value="stage_check">Stage Check</option>
                            <option value="checkride">FAA Practical Test</option>
                        </Form.Control>
                    </Form.Group>
                </Form.Row>

                <TypeOfFlightFormInput
                    categoryOfFlight={categoryOfFlight}
                    typeOfFlight={typeOfFlight}
                    eventHandler={setTypeOfFlight}
                    xcDestination={xcDestination}
                    setXcDestination={setXcDestination}
                />




                <Button variant="primary" onClick={logState}>
                    Submit
                </Button>
            </Form>

        </>
    );
}


export default RiskAssessmentForm;
