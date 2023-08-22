import React, { useEffect, useState } from 'react';

const SseComponent = () => {
    const [data, setData] = useState([]);

    useEffect(() => {
        const eventSource = new EventSource('http://localhost:8080/sse');

        eventSource.onmessage = event => {
            console.log(event);
           const newData = JSON.parse(event.data);
            console.log(newData);
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
        <div>
            <h1>Server-Sent Events in React</h1>
            <ul>
                {data.map((item, index) => (
                    <li key={index}>{item}</li>
                ))}
            </ul>
        </div>
    );
};

export default SseComponent;
