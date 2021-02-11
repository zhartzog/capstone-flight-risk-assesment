import logo from './logo.svg';
import './App.css';
import RiskAssessmentForm from "./Components/RiskAssessmentForm";
import {Jumbotron, Col, Container, Row} from "react-bootstrap";
import React from "react";

function App() {
  return (
    <div className="App">
        <Jumbotron fluid>
            <Container>
                <h1>Risk Assessment Form</h1>
            </Container>
        </Jumbotron>
        <Container>
            <RiskAssessmentForm />
        </Container>
    </div>
  );
}

export default App;
