import 'bootstrap/dist/css/bootstrap.min.css';
import 'react-datepicker/dist/react-datepicker.css';
import RiskAssessmentForm from "./Components/RiskAssessmentForm";
import AdminPanel from "./Components/AdminPanel";
import {Container} from "react-bootstrap";
import React from "react";

function App() {
  return (
      <div className="App">
        <Container fluid>
          <AdminPanel />
        </Container>
      </div>
  );
}

export default App;
