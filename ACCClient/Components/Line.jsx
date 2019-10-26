import React from "react"
import { Card, Container, CardText, CardBody, CardTitle, CardSubtitle, Button, Col, Row, NavLink } from 'reactstrap';


class Line extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            line: this.props.line
        }
    }

    render() {
        return (
            <div >
                <br />
                <div style={{ border: "1px solid #B8B8B8", borderRadius: "15px" }}>
                    <Row style={{ marginLeft: "1%", fontWeight: "bold" }}>
                        <Col>
                            Line Number &nbsp;&nbsp;:&nbsp;&nbsp;{this.state.line.lineNo}
                        </Col>
                    </Row>
                    <Row style={{ marginLeft: "1%", fontWeight: "bold" }}>
                        <Col>
                            Line Data &nbsp;&nbsp;:&nbsp;&nbsp;{this.state.line.data}
                        </Col>
                    </Row>
                    <Row style={{ margin: "1%" }}>
                        <Col>
                            <img width="35" src={require("../images/cs.png")} alt="Language Type" />
                            <CardTitle style={{ color: "#4C4C4C" }}>CS&nbsp;:&nbsp;{this.state.line.cs}</CardTitle>

                        </Col>
                        <Col>
                            <img width="35px" src={require("../images/ctc.png")} alt="Language Type" />
                            <CardTitle style={{ color: "#4C4C4C" }}>CTC&nbsp;:&nbsp;{this.state.line.ctc}</CardTitle>

                        </Col>
                        <Col>
                            <img width="35px" src={require("../images/cnc.png")} alt="Language Type" />
                            <CardTitle style={{ color: "#4C4C4C" }}>CNC&nbsp;:&nbsp;{this.state.line.cnc}</CardTitle>

                        </Col>
                        <Col>
                            <img width="35px" src={require("../images/ci.png")} alt="Language Type" />
                            <CardTitle style={{ color: "#4C4C4C" }}>CI&nbsp;:&nbsp;{this.state.line.ci}</CardTitle>

                        </Col>
                        <Col>
                            <img width="35px" src={require("../images/cps.png")} alt="Language Type" />
                            <CardTitle style={{ color: "#4C4C4C" }}>CPS&nbsp;:&nbsp;{this.state.line.cps}</CardTitle>

                        </Col>
                        <Col>
                            <img width="35px" src={require("../images/tw.png")} alt="Language Type" />
                            <CardTitle style={{ color: "#4C4C4C" }}>TW&nbsp;:&nbsp;{this.state.line.tw}</CardTitle>

                        </Col>
                        <Col>
                            <img width="35px" src={require("../images/cr.png")} alt="Language Type" />
                            <CardTitle style={{ color: "#4C4C4C" }}>CR&nbsp;:&nbsp;{this.state.line.cr}</CardTitle>

                        </Col>
                    </Row>
                </div>
            </div>
        )
    }

}

export default Line