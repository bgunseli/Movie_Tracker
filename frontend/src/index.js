/*!

=========================================================
* Material Kit React - v1.7.0
=========================================================

* Product Page: https://www.creative-tim.com/product/material-kit-react
* Copyright 2019 Creative Tim (https://www.creative-tim.com)
* Licensed under MIT (https://github.com/creativetimofficial/material-kit-react/blob/master/LICENSE.md)

* Coded by Creative Tim

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

*/
import React from "react";
import ReactDOM from "react-dom";
import { createBrowserHistory } from "history";
import { Router, Route, Switch } from "react-router-dom";

import "assets/scss/material-kit-react.scss?v=1.7.0";

// pages for this product
import DirectorPage from "views/MyPages/DirectorPage.jsx";
import ListPage from "views/MyPages/ListPage.jsx";
import LoginPage from "views/MyPages/LoginPage.jsx";
import MoviePage from "views/MyPages/MoviePage.jsx";
import UserPage from "views/MyPages/UserPage.jsx";

var hist = createBrowserHistory();

ReactDOM.render(
  <Router history={hist}>
    <Switch>
      <Route path="/directors" component={DirectorPage} />
      <Route path="/lists" component={ListPage} />
      <Route path="/login" component={LoginPage} />
      <Route path="/movies" component={MoviePage} />
      <Route path="/users" component={UserPage} />
    </Switch>
  </Router>,
  document.getElementById("root")
);
