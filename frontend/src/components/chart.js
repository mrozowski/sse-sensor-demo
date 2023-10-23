import './block.css';
import React, {Component} from 'react';
import {Line} from 'react-chartjs-2';

class Chart extends Component {
    constructor(props) {
        super(props);
        this.state = {
            data: {
                labels: [], // Timestamps
                datasets: [
                    {
                        label: 'Temperature',
                        data: [], // Temperature values
                        borderColor: 'red',
                    },
                    {
                        label: 'Humidity',
                        data: [], // Humidity values
                        borderColor: 'blue',
                    },
                ],
            },
        };
    }
}

export default Chart;
