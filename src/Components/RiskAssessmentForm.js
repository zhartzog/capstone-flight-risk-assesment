import React, { Component } from 'react';

import {Container, Row, Col, Form, FormGroup} from 'react-bootstrap';

function RiskAssessmentForm() {
    return (
        <>
            <Form>
                <FormGroup>
                    <Form.Label>Student's Name: </Form.Label>
                    <Form.Control type="text"></Form.Control>
                </FormGroup>
            </Form>
        </>
    );
}

export default RiskAssessmentForm;
