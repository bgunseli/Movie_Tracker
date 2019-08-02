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
import "views/MyPages/css/MoviePageUpdate.css"
import { maxWidth } from "@material-ui/system";

class MoviePageUpdate extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            cardAnimaton: "cardHidden",
            movie: [],
            id: "",
            name: "",
            directorName: "",
            releaseDate: "",
            imdbRating: "",
            duration: "",
            movieGenre: "",
            poster: "",
            headerName: ""
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
                console.log("response")
                console.log(response)
                this.setState({ movie: response.data })
                var movie = this.state.movie
                var director = movie && movie.director ? movie.director.name : null;
                var releaseDate = movie && movie.releaseDate ? movie.releaseDate : null;
                if (releaseDate != null) {
                    var date = releaseDate.substring(0, 10);
                }
                this.setState({ id: movie.id })
                this.setState({ name: movie.name })
                this.setState({ directorName: director })
                this.setState({ releaseDate: date })
                this.setState({ imdbRating: movie.imdbRating })
                this.setState({ duration: movie.duration })
                this.setState({ movieGenre: movie.movieGenre })
                this.setState({ poster: movie.poster })
                this.setState({ headerName: movie.name })
            })
    }

    updateClick = () => {
        this.setState({[this.state.movie.director.name]: this.state.directorName})
        axios.put("http://localhost:8080/movies/" + this.state.id, {
            id: this.state.id,
            name: this.state.name,
            director: this.state.movie.director,
            releaseDate: this.state.releaseDate,
            imdbRating: this.state.imdbRating,
            duration: this.state.duration,
            movieGenre: this.state.movieGenre,
            poster: this.state.poster
        })
    }

    inputChange = (event) => {
        this.setState({ [event.target.name]: event.target.value })
    }

    render() {
        const { classes, ...rest } = this.props;
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
                            <GridItem xs={12} sm={12} md={8} >
                                <Card className={classes[this.state.cardAnimaton]}>
                                    <CardHeader color="info" className={classes.cardHeader}>
                                        <h4>{this.state.headerName}</h4>
                                    </CardHeader>
                                    <CardBody>
                                        <div className='rowC' style={{ margin: "20px" }}>
                                            <div>
                                                <img src={this.state.poster} alt="Italian Trulli" height="200px " />
                                            </div>
                                            <div style={{ maxWidth: "350px", marginLeft: "20px" }}>
                                                <CustomInput
                                                    labelText="ID"
                                                    id="id"
                                                    formControlProps={{
                                                        fullWidth: true,
                                                        disabled: true
                                                    }}
                                                    inputProps={{
                                                        type: "text",
                                                        name: "name",
                                                        value: this.state.id,
                                                        onChange: this.inputChange
                                                    }}
                                                />
                                                <CustomInput
                                                    labelText="Name"
                                                    id="name"
                                                    formControlProps={{
                                                        fullWidth: true
                                                    }}
                                                    inputProps={{
                                                        type: "text",
                                                        name: "name",
                                                        value: this.state.name,
                                                        onChange: this.inputChange
                                                    }}
                                                />
                                                <CustomInput
                                                    labelText="Director"
                                                    id="director"
                                                    formControlProps={{
                                                        fullWidth: true
                                                    }}
                                                    inputProps={{
                                                        type: "text",
                                                        name: "directorName",
                                                        value: this.state.directorName,
                                                        onChange: this.inputChange
                                                    }}
                                                />
                                                <CustomInput
                                                    labelText="Release Date"
                                                    id="releaseDate"
                                                    formControlProps={{
                                                        fullWidth: true
                                                    }}
                                                    inputProps={{
                                                        type: "text",
                                                        name: "releaseDate",
                                                        value: this.state.releaseDate,
                                                        onChange: this.inputChange
                                                    }}
                                                />
                                                <CustomInput
                                                    labelText="Imdb Rating"
                                                    id="imdbRating"
                                                    formControlProps={{
                                                        fullWidth: true
                                                    }}
                                                    inputProps={{
                                                        type: "text",
                                                        name: "imdbRating",
                                                        value: this.state.imdbRating,
                                                        onChange: this.inputChange
                                                    }}
                                                />
                                                <CustomInput
                                                    labelText="Duration"
                                                    id="duration"
                                                    formControlProps={{
                                                        fullWidth: true
                                                    }}
                                                    inputProps={{
                                                        type: "text",
                                                        name: "duration",
                                                        value: this.state.duration,
                                                        onChange: this.inputChange
                                                    }}
                                                />
                                                <CustomInput
                                                    labelText="Genre"
                                                    id="movieGenre"
                                                    formControlProps={{
                                                        fullWidth: true
                                                    }}
                                                    inputProps={{
                                                        type: "text",
                                                        name: "movieGenre",
                                                        value: this.state.movieGenre,
                                                        onChange: this.inputChange
                                                    }}
                                                />
                                                <CustomInput
                                                    labelText="Poster"
                                                    id="poster"
                                                    formControlProps={{
                                                        fullWidth: true
                                                    }}
                                                    inputProps={{
                                                        type: "text",
                                                        name: "poster",
                                                        value: this.state.poster,
                                                        onChange: this.inputChange
                                                    }}
                                                />
                                                <Button color="info" style={{ float: "right" }} onClick={this.updateClick} >Update</Button>
                                            </div>
                                        </div>
                                    </CardBody>
                                </Card>
                            </GridItem>
                        </GridContainer>
                    </div>

                </div>
            </div>
        );
    }
}

MoviePageUpdate.propTypes = {
    classes: PropTypes.object
};

export default withStyles(loginPageStyle)(MoviePageUpdate);
