import React, {useEffect, useState} from 'react';
import Block from "./components/block";
import Chart from "./components/chart"

const SseComponent = () => {
    const [temperature, setTemperature] = useState(0.00);
    const [humidity, setHumidity] = useState(0);
    const [data, setData] = useState([]);
    useEffect(() => {
        const eventSource = new EventSource('http://localhost:8080/sse');

        eventSource.onmessage = event => {
            console.log(event);
            const newData = JSON.parse(event.data);
            const { temperature, humidity } = newData;


            setTemperature(temperature);
            setHumidity(humidity);
            setData(prevData => [...prevData, newData]);

        };

        eventSource.onerror = error => {
            console.error('Error:', error);
        };

        return () => {
            eventSource.close(); // Clean up the EventSource connection
        };
    }, []);

    return (
        <div className="sse-component">
            <Chart data={data}/>
            <div className={"block-section"}>
                <Block temp={temperature} title="Temperature" postfix="°C"/>
                <Block temp={humidity} title="Humidity" postfix="%"/>
            </div>
        </div>
    );
};

export default SseComponent;
