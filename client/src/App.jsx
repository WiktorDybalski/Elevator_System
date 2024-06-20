import React, {useEffect, useState} from 'react';
import ElevatorInfo from "./ElevatorInfo.jsx";
import FloorLayout from "./FloorLayout.jsx";
import './App.css';

function App() {
    const [elevators, setElevators] = useState([]);
    const [simulationStarted, setSimulationStarted] = useState(false);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const runningResponse = await fetch('http://localhost:8080/api/elevators/isRunning');
                const isRunning = await runningResponse.json();
                setSimulationStarted(isRunning);

                if (isRunning) {
                    const response = await fetch('http://localhost:8080/api/elevators/status');
                    const data = await response.json();
                    setElevators(data);
                }
            } catch (error) {
                console.error('Error fetching elevator status:', error);
            }
        };

        fetchData();
        const interval = setInterval(fetchData, 2000);

        return () => clearInterval(interval);
    }, []);

    return (
        <div className="app">
            <header className="header">
                <h1>Elevator Management System</h1>
            </header>
            <div className="container">
                <div className="sidebar">
                    <ElevatorInfo
                        elevators={elevators}
                        setElevators={setElevators}
                    />
                </div>
                <div className="main-content">
                    <FloorLayout elevators={elevators}
                                 simulationStarted={simulationStarted}
                                 setSimulationStarted={setSimulationStarted}
                    />
                </div>
            </div>
        </div>
    );
}

export default App;


