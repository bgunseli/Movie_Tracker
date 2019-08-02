import Icon from "@material-ui/core/Icon";
import InputAdornment from "@material-ui/core/InputAdornment";
// @material-ui/core components
import withStyles from "@material-ui/core/styles/withStyles";
import People from "@material-ui/icons/People";
import image from "assets/img/login_background.jpg";
import loginPageStyle from "assets/jss/material-kit-react/views/loginPage.jsx";
import axios from 'axios';
import Card from "components/Card/Card.jsx";
import CardBody from "components/Card/CardBody.jsx";
import CardFooter from "components/Card/CardFooter.jsx";
import CardHeader from "components/Card/CardHeader.jsx";
import Button from "components/CustomButtons/Button.jsx";
import CustomInput from "components/CustomInput/CustomInput.jsx";
import GridContainer from "components/Grid/GridContainer.jsx";
import GridItem from "components/Grid/GridItem.jsx";
// nodejs library to set properties for components
import PropTypes from "prop-types";
import React from "react";
import "views/MyPages/css/MoviePage.css"
import MoviePageUpdate from "./MoviePageUpdate";
import { confirmAlert } from 'react-confirm-alert'; // Import
import 'react-confirm-alert/src/react-confirm-alert.css'; // Import css

class ListPageLists extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            cardAnimaton: "cardHidden",
            movies: [],
            updatePage: false,
            link: ""
        };
    }

    componentDidMount() {
        setTimeout(
            function () {
                this.setState({ cardAnimaton: "" });
            }.bind(this),
            700
        );
        axios.get(this.props.link)
            .then(response => {
                this.setState({ movies: response.data })
            })
    }

    tableFunction = (movie) => {
        return (
            <table>
                <thead>
                    <tr>
                        <th>Poster</th>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Director</th>
                        <th>Release Date</th>
                        <th>Imdb Rating</th>
                        <th>Duration</th>
                        <th>Genre</th>
                    </tr>
                </thead>
                <tbody>
                    {movie.map(this.tableRow)}
                </tbody>

            </table>
        )
    }

    tableRow = (movie, a) => {
        return (
            <tr key={a}>
                <td>
                    <img src={movie.poster} alt="Italian Trulli" height="100px " />
                </td>
                <td style={{ fontWeight: "bold" }}>{movie.id}</td>
                <td style={{ maxWidth: "200px" }}>{movie.name}</td>
                <td style={{ fontWeight: "bold" }}>{movie.director.name}</td>
                <td>{movie.releaseDate.substring(0, 10)}</td>
                <td style={{ fontWeight: "bold" }}>{movie.imdbRating}</td>
                <td>{movie.duration}</td>
                <td style={{ fontWeight: "bold", maxWidth: "200px" }}>{movie.movieGenre}</td>
            </tr>
        )
    }

    render() {
        const { classes, ...rest } = this.props;
        if (this.state.updatePage) {
            return (<MoviePageUpdate link={this.state.link} />);
        }
        else {
            return (
                <div>
                    <div
                        className={classes.pageHeader}
                        style={{
                            backgroundImage: "url(" + image + ")",
                            backgroundSize: "cover",
                            backgroundPosition: "top center"
                        }}
                    >
                        <div className={classes.container}>
                            <GridContainer justify="center">
                                <GridItem xs={12} sm={12} md={12} >
                                    <Card className={classes[this.state.cardAnimaton]}>
                                        {this.tableFunction(this.state.movies)}
                                    </Card>
                                </GridItem>
                            </GridContainer>
                        </div>

                    </div>
                </div>
            );
        }
    }
}

ListPageLists.propTypes = {
    classes: PropTypes.object
};

export default withStyles(loginPageStyle)(ListPageLists);
