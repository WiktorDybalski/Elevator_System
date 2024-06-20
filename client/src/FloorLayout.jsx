import React from 'react';
import './styles/FloorLayout.css';

const FloorLayout = ({ elevators, simulationStarted, setSimulationStarted }) => {
    const floors = Array.from({ length: 17 }, (_, i) => `${i}`);
    const elevatorsCount = Array.from({ length: 16 }, (_, i) => `E${i + 1}`);

    const startSimulation = async () => {
        if (simulationStarted) {
            return;
        }
        try {
            await fetch('http://localhost:8080/api/elevators/start', { method: 'POST' });
            setSimulationStarted(true);
        } catch (error) {
            console.error('Error starting simulation:', error);
        }
    };

    const stopSimulation = async () => {
        try {
            await fetch('http://localhost:8080/api/elevators/stop', { method: 'POST' });
            setSimulationStarted(false);
        } catch (error) {
            console.error('Error stopping simulation:', error);
        }
    };

    const handlePickupRequest = async () => {
        const pickupFloor = document.getElementById('pickup-floor').value;
        const targetFloor = document.getElementById('target-floor').value;
        if (pickupFloor === '' || targetFloor === '' || isNaN(pickupFloor) || isNaN(targetFloor)) {
            alert('Please enter pickup and target floor numbers.');
            return
        } else if(pickupFloor < 0 || pickupFloor > 16 || targetFloor < 0 || targetFloor > 16) {
            alert('Please enter a valid floor number between 0 and 16.');
            return
        }
        try {
            await fetch(`http://localhost:8080/api/elevators/pickup?pickupFloor=${pickupFloor}&targetFloor=${targetFloor}`, {
                method: 'POST'
            });
            document.getElementById('pickup-floor').value = '';
            document.getElementById('target-floor').value = '';
        } catch (error) {
            console.error('Error sending pickup request:', error);
        }
    };

    return (
        <div>
            <h2>Add route</h2>
            <div id="pickup-button-container">
                <input id="pickup-floor" type="text" placeholder="Pickup floor"/>
                <input id="target-floor" type="text" placeholder="Target floor"/>
                <button id="pickup-button" onClick={handlePickupRequest}>Pickup</button>
            </div>
            <div id="run-button-container">
                <button id="run-button" onClick={startSimulation} disabled={simulationStarted}>Start</button>
                <button id="stop-button" onClick={stopSimulation} disabled={!simulationStarted}>Stop</button>
            </div>
            <div className="floor-layout">
                {floors.map((floor, floorIndex) => (
                    <div key={floorIndex} className="floor">
                        <div className="floor-number">{floor}</div>
                        <div className="elevators">
                            {elevatorsCount.map((_, elevatorIndex) => {
                                const isElevatorOnFloor = elevators.some(e => e.id === elevatorIndex && e.currentFloor === floorIndex);
                                return (
                                    <div
                                        key={elevatorIndex}
                                        className={`elevator ${isElevatorOnFloor ? 'current-floor' : ''}`}
                                    ></div>
                                );
                            })}
                        </div>
                    </div>
                ))}
                <div className="floor">
                    <div className="floor-number"></div>
                    <div className="elevators">
                        {elevatorsCount.map((elevator, elevatorIndex) => (
                            <div key={elevatorIndex} className="elevator-name">{elevator}</div>
                        ))}
                    </div>
                </div>
            </div>
        </div>
    );
}

export default FloorLayout;

