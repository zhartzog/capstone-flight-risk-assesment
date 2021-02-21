
import 'bootstrap/dist/css/bootstrap.min.css';
import 'react-datepicker/dist/react-datepicker.css';
import RiskAssessmentForm from "./Components/RiskAssessmentForm";
import {Jumbotron, Col, Container, Row} from "react-bootstrap";
import React from "react";

function App() {
  return (
      <div className="App">
        <Container fluid>
          <RiskAssessmentForm />
        </Container>
      </div>
  );
}

export default App;
