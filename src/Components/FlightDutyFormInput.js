import React from 'react';
import {Col, Form} from 'react-bootstrap';
import DatePicker from 'react-datepicker';

/*
   Allow the user to select the beginning of their flight duty. In other words, how long have they been
   flying today. This question is only important if they've had more than one flight today.
 */
function FlightDutyFormInput(props){
    if(props.prevFlights < 1)
        return('');
    else{
        return(
            <>
                <Form.Row>
                    <Form.Group as={Col} controlId="flightDuty">
                        <Form.Label className="mr-3">Beginning of Flight Duty: </Form.Label>
                        <DatePicker
                            selected={props.flightDuty}
                            onChange={date => props.eventHandler(date)}
                            showTimeSelect
                            showTimeSelectOnly
                            timeIntervals={15}
                            timeCaption="Time"
                            dateFormat="h:mm aa"
                        />
                    </Form.Group>
                </Form.Row>
            </>
        );
    }
}

export default FlightDutyFormInput;
