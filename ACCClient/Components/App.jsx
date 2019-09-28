import React from "react"
import Home from "./Home"
import History from "./History"
import Files from "./Files"
import { BrowserRouter as Router, Redirect, Route } from "react-router-dom"
import "../style.css"

class App extends React.Component {

    constructor(props) {
        super(props)
        this.state = {

        }
    }

    render() {
        return (
            <div>
                <Router>

                    <Route path="/" exact strict render={() => {
                        return (<Redirect push to="/home" />)
                    }} />

                    <Route path="/home" exact strict render={(props) => {
                        return (<Home {...props} />)
                    }} />

                    <Route path="/history" exact strict render={(props) => {
                        return (<History {...props} />)
                    }} />

                    <Route path="/home/files" exact strict render={(props) => {
                        return (<Files {...props} />)
                    }} />

                </Router>
            </div>
        )
    }
}

export default App