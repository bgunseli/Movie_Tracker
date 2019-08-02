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

class LoginPage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            cardAnimaton: "cardHidden",
            lists: []
        };
    }
    componentDidMount() {
        // we add a hidden class to the card and after 700 ms we delete it and the transition appears
        setTimeout(
            function () {
                this.setState({ cardAnimaton: "" });
            }.bind(this),
            700
        );
        axios.get("http://localhost:8080/lists")
            .then(response => {
                console.log(response)
                this.setState({ lists: response.data })
            })
    }

    buttonList = (list) => {
        return(
            <div>
                <Button large color="info" size="lg" onClick={this.onClick}></Button>
            </div>
        );
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
                            <GridItem xs={12} sm={12} md={12}>
                                <Card className={classes[this.state.cardAnimaton]}>
                                    {this.state.lists.map(this.buttonList)}
                                </Card>
                            </GridItem>
                        </GridContainer>
                    </div>
                </div>
            </div>
        );
    }
}

LoginPage.propTypes = {
    classes: PropTypes.object
};

export default withStyles(loginPageStyle)(LoginPage);
