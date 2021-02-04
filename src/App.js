import React, {useEffect, useState } from 'react';

import logo from './logo.svg';
import './App.css';

import axios from "axios";

function App() {
  const [quote, setQuote] = useState(null);

  useEffect(() =>{
      axios.get(`/api/test`)
          .then(res => {
              setQuote(res.data);
          })
  },[]);

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
            {quote}
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
      <h1>{quote}</h1>
    </div>
  );
}

export default App;
