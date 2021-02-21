import React from 'react';
import {Col, Form} from 'react-bootstrap';
function TypeOfFlightFormInput(props){
    //stage checks and checkrides are always local flights
    if(props.categoryOfFlight === "stage_check" || props.categoryOfFlight === "checkride")
        return('');
    else if(props.categoryOfFlight === "normal" && props.typeOfFlight !== "cross_country"){
        return(
            <>
                <Form.Row>
                    <Form.Group as={Col} controlId="missionSelect">
                        <Form.Label>Type Of Flight</Form.Label>
                        <Form.Control
                            as="select"
                            name="student_level"
                            onChange={e => props.eventHandler(e.target.value)}
                            value={props.typeOfFlight}
                        >
                            <option value="pattern">Local Pattern</option>
                            <option value="practice_area">Practice Area</option>
                            <option value="aux_field">Auxiliary Field</option>
                            <option value="cross_country">Cross Country</option>
                        </Form.Control>

                    </Form.Group>
                </Form.Row>
            </>
        );
    }
    else{
        return(
            <>
                <Form.Row>
                    <Form.Group as={Col} controlId="missionSelect">
                        <Form.Label>Type Of Flight</Form.Label>
                        <Form.Control
                            as="select"
                            name="student_level"
                            onChange={e => props.eventHandler(e.target.value)}
                            value={props.typeOfFlight}
                        >
                            <option value="pattern">Local Pattern</option>
                            <option value="practice_area">Practice Area</option>
                            <option value="aux_field">Auxiliary Field</option>
                            <option value="cross_country">Cross Country</option>
                        </Form.Control>
                    </Form.Group>
                </Form.Row>

                <Form.Row>
                    <Form.Group as={Col} controlId="xc_select">
                        <Form.Label>Cross Country Destination(s) </Form.Label>
                        <Form.Control
                            type="text"
                            name="xc_airport"
                            onChange={e => props.setXcDestination(e.target.value)}
                            className="departureAirport"
                            placeholder="KLNK,KOLU,KOMA"
                            value={props.xcDestination}
                        />
                    </Form.Group>
                </Form.Row>
            </>
        );
    }
}

export default TypeOfFlightFormInput;
