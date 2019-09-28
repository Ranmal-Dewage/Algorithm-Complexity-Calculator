import React from "react"
import NavBar from "./NavBar"
import Project from "./Project"

class Home extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            projects: [],
            isLoading: false
        }
    }

    componentDidMount() {
        this.setState({ isLoading: false })
        fetch("http://localhost:8090/projects")
            .then(res => res.json())
            .then(res => {
                this.setState({ projects: res, isLoading: true })
            })
    }


    render() {
        if(this.state.length != 0){
            let i=1
            var Projects = this.state.projects.map((project)=>{
                return <Project key={i++} project={project} {...this.props}/>
            })
        }

        return (
            <div>
                <NavBar {...this.props}/>
                {Projects}
            </div>
        )
    }

}

export default Home