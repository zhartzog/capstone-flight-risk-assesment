
import 'bootstrap/dist/css/bootstrap.min.css';
import 'react-datepicker/dist/react-datepicker.css';
import RiskAssessmentForm from "./Components/RiskAssessmentForm";
import {Container} from "react-bootstrap";
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
