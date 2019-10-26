import React from "react"
import { Card, Container, CardText, CardBody, CardTitle, CardSubtitle, Button, Col, Row, NavLink } from 'reactstrap';
import { MDBBtn, MDBCard, MDBCardBody, MDBCardImage, MDBCardTitle, MDBCardText, MDBCol } from 'mdbreact';
import { Icon } from 'react-icons-kit'
import { filesEmpty } from 'react-icons-kit/icomoon/filesEmpty'
import { statsDots } from 'react-icons-kit/icomoon/statsDots'
import { fileCodeO } from 'react-icons-kit/fa/fileCodeO'
import { history } from 'react-icons-kit/icomoon/history'
import config from "../config.json"



class Project extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            project: this.props.project,
            history: []
        }
        this.handleHistoryClick = this.handleHistoryClick.bind(this)
        this.handleFilesClick = this.handleFilesClick.bind(this)
    }

    componentDidMount() {
        var key = this.state.project.projectKey
        fetch(config.backendUrl + "projects/" + key + "/history").
            then(res => res.json()).
            then(res => {
                var his = []
                res.map(data => {
                    var pHistory = data.project.cp / data.project.files.length
                    his = [...his, pHistory]
                })
                his = his.reverse()
                his = his.slice(0, 6);
                console.log(his)
                this.setState({
                    history: his.reverse()
                })
            })
    }

    handleHistoryClick() {
        var key = this.state.project.projectKey
        console.log(key)
        this.props.history.push("/history", { res: this.state.history })
    }

    handleFilesClick() {
        this.props.history.push("/home/files", { res: this.state.project.files })
    }

    render() {
        var avgComplexity = this.state.project.cp / this.state.project.files.length
        return (
            <div>
                <br />
                <Container>
                    <Col>
                        <Card style={{ backgroundColor: "#EFEFEF" }}>
                            <CardBody>
                                <Row style={{ margin: "1%" }}>
                                    <Col>
                                        <MDBCardTitle>
                                            <span style={{ fontWeight: "bold", borderBottom: "1px solid #000000" }}>
                                                {this.state.project.projectKey}&nbsp;&nbsp;
                                            </span>
                                            {
                                                avgComplexity < 200 ? <span className="pass">Pass</span>
                                                    : <span className="error">Fail</span>
                                            }
                                        </MDBCardTitle>
                                    </Col>
                                </Row>
                                <Row>
                                    <Col style={{ borderRight: "1px solid #B8B8B8", margin: "1%" }}>
                                        <NavLink href="#">
                                            <Icon style={{ marginRight: "2%", color: "#209B3B" }} size={40} icon={statsDots} />
                                            <CardTitle style={{ color: "#4C4C4C" }}>Average Complexity&nbsp;:&nbsp;{avgComplexity}</CardTitle>
                                        </NavLink>
                                    </Col>
                                    <Col style={{ borderRight: "1px solid #B8B8B8", margin: "1%" }}>
                                        <NavLink href="#">
                                            <Icon style={{ marginRight: "2%", color: "#CF912A" }} size={40} icon={filesEmpty} />
                                            <CardTitle style={{ color: "#4C4C4C" }}>Number of Files&nbsp;:&nbsp;{this.state.project.files.length}</CardTitle>
                                        </NavLink>
                                    </Col>
                                    <Col style={{ borderRight: "1px solid #B8B8B8", margin: "1%" }}>
                                        <NavLink href="#">
                                            <Icon style={{ marginRight: "2%", color: "#A4422F" }} size={40} icon={fileCodeO} />
                                            <CardTitle style={{ color: "#4C4C4C" }}>Language&nbsp;:&nbsp;{this.state.project.language}</CardTitle>
                                        </NavLink>
                                    </Col>
                                    <Col style={{ margin: "1%" }}>
                                        <NavLink className="projNavLink" href="#" onClick={this.handleHistoryClick}>
                                            <Icon style={{ marginRight: "2%" }} size={40} icon={history} />
                                            <CardTitle style={{ color: "#4C4C4C" }}>History</CardTitle>
                                        </NavLink>
                                    </Col>
                                    <Col>
                                        <MDBBtn color="primary" onClick={this.handleFilesClick}>View Details</MDBBtn>
                                    </Col>
                                </Row>
                            </CardBody>
                        </Card>
                    </Col>
                </Container>
            </div>
        )
    }

}

export default Project