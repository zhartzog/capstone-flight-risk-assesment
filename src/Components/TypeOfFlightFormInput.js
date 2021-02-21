import React from 'react';
import {Col, Form} from 'react-bootstrap';
/*
    This component will allow the user to select the type of flight.
    These fields should only appear when the user selects "normal".
    The component will also allow the user to enter the destinations for their cross country.
    This field should only pop up when the user has selected "cross_country".
 */
function TypeOfFlightFormInput(props){
    //stage checks and checkrides are always local flights
    if(props.categoryOfFlight === "stage_check" || props.categoryOfFlight === "checkride")
        return('');
    else if(props.categoryOfFlight === "normal" && props.typeOfFlight !== "cross_country"){
        /*Return only the Type of Flight dropdown. Keep the cross country destination fields hidden.*/
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
