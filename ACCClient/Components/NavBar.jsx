import React from "react"
import {
    Collapse,
    Navbar,
    NavbarToggler,
    NavbarBrand,
    Nav,
    NavItem,
    NavLink
} from "reactstrap"
import { Icon } from 'react-icons-kit'
import { home } from 'react-icons-kit/icomoon/home'
import { users } from 'react-icons-kit/icomoon/users'
import "../style.css"

class NavBar extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            isOpen: false
        }
        this.toggle = this.toggle.bind(this);
    }

    toggle() {
        this.setState({
            isOpen: !this.state.isOpen
        });
    }

    render() {
        return (
            <div>
                <Navbar color="light" light expand="md">
                    <NavbarBrand className="navBarImg" href="/home"><img className="mainImg" src={require("../images/ACC_Logo.png")} alt="ACC Logo" />
                        <span style={{ color: "#103466", marginLeft: "1%", fontWeight: "bold" }}>Algorithm Complexity Calculator</span>
                    </NavbarBrand>

                    <NavbarToggler onClick={this.toggle} />
                    <Collapse isOpen={this.state.isOpen} navbar>
                        <Nav className="ml-auto" navbar>
                            <NavItem>
                                <NavLink href="/home">
                                    <div>
                                        <Icon style={{ marginRight: "1%" }} size={20} icon={home} />Home
                                    </div>
                                </NavLink>
                            </NavItem>
                            <NavItem>
                                <NavLink href="#">
                                    <div>
                                        <Icon style={{ marginRight: "1%" }} size={20} icon={users} />About
                                    </div>
                                </NavLink>
                            </NavItem>
                        </Nav>
                    </Collapse>
                </Navbar>
            </div>
        )
    }
}

export default NavBar