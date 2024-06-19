import React from 'react';
import './styles/FloorLayout.css';

const FloorLayout = ({ elevators }) => {
    const floors = Array.from({ length: 17 }, (_, i) => `Floor ${i}`);
    const elevatorsCount = Array.from({ length: 16 }, (_, i) => `E${i + 1}`);

    return (
        <div>
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

