import React, { Component } from "react";
import logo from "./logo.svg";
import "./App.css";

class App extends Component {
  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <p>WELCOME TO ESPARK ADARSH</p>
          <a
            className="App-link"
            href="http://adarshkumarsingh83.blogspot.com"
            target="_blank"
            rel="noopener noreferrer"
          >
            catch me
          </a>
        </header>
      </div>
    );
  }
}

export default App;
