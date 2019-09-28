import React from "react"
import NavBar from "./NavBar"
import { Bar } from "react-chartjs-2";
import { MDBContainer } from "mdbreact";


class History extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            dataBar: {
                labels: ["Version 1", "Version 2", "Version 3", "Version 4", "Version 5", "Version 6",],
                datasets: [
                    {
                        label: "Average CP of a Projects",
                        data: this.props.location.state.res,
                        backgroundColor: [
                            "rgba(255, 134,159,0.4)",
                            "rgba(98,  182, 239,0.4)",
                            "rgba(255, 218, 128,0.4)",
                            "rgba(113, 205, 205,0.4)",
                            "rgba(170, 128, 252,0.4)",
                            "rgba(255, 177, 101,0.4)"
                        ],
                        borderWidth: 2,
                        borderColor: [
                            "rgba(255, 134, 159, 1)",
                            "rgba(98,  182, 239, 1)",
                            "rgba(255, 218, 128, 1)",
                            "rgba(113, 205, 205, 1)",
                            "rgba(170, 128, 252, 1)",
                            "rgba(255, 177, 101, 1)"
                        ]
                    }
                ]
            },
            barChartOptions: {
                responsive: true,
                maintainAspectRatio: true,
                scales: {
                    xAxes: [
                        {
                            barPercentage: 1,
                            gridLines: {
                                display: true,
                                color: "rgba(0, 0, 0, 0.1)"
                            }
                        }
                    ],
                    yAxes: [
                        {
                            gridLines: {
                                display: true,
                                color: "rgba(0, 0, 0, 0.1)"
                            },
                            ticks: {
                                beginAtZero: true
                            }
                        }
                    ]
                }
            }
        }
    }

    componentDidMount() {
       
    }

    render() {
        return (
            <div>
                <NavBar />
                <br />
                <MDBContainer>
                    <h3 style={{ fontWeight: "bold" }}>CP Values of Previous Projects</h3>
                    <Bar data={this.state.dataBar} options={this.state.barChartOptions} />
                </MDBContainer>
            </div>
        )
    }

}

export default History