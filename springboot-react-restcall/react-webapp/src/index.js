import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import App from "./App";
import RestController from "./component/RestController";
import CategoryPost from "./component/CategoryPost";
import * as serviceWorker from "./serviceWorker";

ReactDOM.render(<CategoryPost />, document.getElementById("root1"));
ReactDOM.render(<RestController />, document.getElementById("root2"));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: http://bit.ly/CRA-PWA
serviceWorker.unregister();
