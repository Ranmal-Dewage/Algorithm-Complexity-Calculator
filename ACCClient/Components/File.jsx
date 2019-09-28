import React from "react"
import { Card, Container, CardText, CardBody, CardTitle, CardSubtitle, Button, Col, Row, Collapse } from 'reactstrap';
import { MDBBtn, MDBCard, MDBCardBody, MDBCardImage, MDBCardTitle, MDBCardText, MDBCol } from 'mdbreact';
import { Icon } from 'react-icons-kit'
import { fileText } from 'react-icons-kit/feather/fileText'
import Line from "./Line"

class File extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            collapse: false,
            file: this.props.file
        }
        this.toggle = this.toggle.bind(this);
    }

    toggle() {
        this.setState(state => (
            { collapse: !state.collapse }
        ));
    }

    render() {
        var i = 1
        const Lines = this.state.file.linesData.map((line) => {
            return <Line key={i++} line={line} {...this.props} />
        })

        return (
            <div>
                <Container>
                    <Col>
                        <Card style={{ backgroundColor: "#EFEFEF" }}>
                            <CardBody>
                                <Row style={{ margin: "1%" }}>
                                    <Col>
                                        <MDBCardTitle>
                                            <span style={{ fontWeight: "bold", borderBottom: "1px solid #000000" }}>
                                                {this.state.file.relativePath.substring(1)}&nbsp;&nbsp;
                                            </span>
                                            {this.state.file.relativePath.substring(this.state.file.relativePath.indexOf('.') + 1) == "java" ?
                                                <img width="65px" src={require("../images/java.png")} alt="Language Type" /> :
                                                <img width="65px" src={require("../images/cpp.png")} alt="Language Type" />
                                            }
                                        </MDBCardTitle>
                                    </Col>
                                </Row>
                                <Row style={{ margin: "1%" }}>
                                    <Col>
                                        <Icon style={{ marginRight: "2%", color: "#209B3B" }} size={48} icon={fileText} />
                                        <CardTitle style={{ color: "#4C4C4C" }}>Number of Lines&nbsp;:&nbsp;{this.state.file.linesData.length}</CardTitle>
                                    </Col>
                                    <Col>
                                        <img width="48px" src={require("../images/cp.png")} alt="Language Type" />
                                        <CardTitle style={{ color: "#4C4C4C" }}>CP&nbsp;:&nbsp;{this.state.file.cp}</CardTitle>

                                    </Col>
                                    <Col>
                                        <MDBBtn color="primary" onClick={this.toggle} style={{ marginBottom: '1rem' }}>Expolre</MDBBtn>
                                    </Col>
                                </Row>
                                <Collapse isOpen={this.state.collapse}>
                                    {Lines}
                                </Collapse>
                            </CardBody>
                        </Card>
                    </Col>
                </Container>
            </div>
        )
    }

}

export default File