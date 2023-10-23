import React, {useEffect, useState} from 'react';
import Block from "./components/block";

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

            //setData(prevData => [...prevData, newData]);
            setTemperature(temperature);
            setHumidity(humidity);
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
            <Block temp={temperature} title="Temperature" postfix="°C"/>
            <Block temp={humidity} title="Humidity" postfix="%"/>
            {/*<ul>*/}
            {/*    {data.map((item, index) => (*/}
            {/*        <li key={index}>{item}</li>*/}
            {/*    ))}*/}
            {/*</ul>*/}
        </div>
    );
};

export default SseComponent;
