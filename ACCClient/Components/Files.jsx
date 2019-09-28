import React from "react"
import NavBar from "./NavBar"
import File from "./File"

class Files extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            files: this.props.location.state.res
        }
    }

    render() {
        var i = 1
        const Files = this.state.files.map((file) => {
            return (
                <div key={i++}>
                    <File key={i++} file={file} {...this.props} />
                    <br key={i++} />
                </div>
            )
        })
        return (
            <div>
                <NavBar {...this.props} />
                <br />
                {Files}
            </div>
        )
    }

}

export default Files