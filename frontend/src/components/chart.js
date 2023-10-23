import './chart.css';
import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Legend,

} from "chart.js";
import {Line} from "react-chartjs-2";

ChartJS.register(
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Legend
);

const Chart = (data) => {
    const labels = ["2017", "2018", "2019", "2020", "2021", "2022"];
    const temperatureData = data.map(entry => entry.temperature);
    const humidityData = data.map(entry => entry.humidity);
    const options = {
        plugins: {
            legend: {
                position: "bottom",
            },
        },
    };
    const dataInput = {
        labels: labels, // Timestamps
        datasets: [
            {
                label: 'Temperature',
                data: temperatureData,
                borderColor: 'red',
            },
            {
                label: 'Humidity',
                data: humidityData,
                borderColor: 'blue',
            },
        ],
    }



    return (
        <div className={"chart-block"}>
            <Line className={"line"} data={dataInput} options={options}/>
        </div>
    )
}

export default Chart;
