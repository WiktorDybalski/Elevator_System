import React, { useEffect } from 'react';
import './styles/ElevatorInfo.css';

const ElevatorInfo = ({ elevators}) => {

    return (
        <div>
            {elevators.length > 0 ? (
                <ul>
                    {elevators.map(elevator => (
                        <li key={elevator.id}>
                            ID: {elevator.id}, Current Floor: {elevator.currentFloor}, State: {elevator.state},
                            UpRequests: {elevator.upRequests && elevator.upRequests.length > 0 ? '[ ' + elevator.upRequests.join(', ') + ' ]' : '[]'},
                            DownRequests: {elevator.downRequests && elevator.downRequests.length > 0 ? '[ ' + elevator.downRequests.join(', ') + ' ]' : '[]'}
                        </li>
                    ))}
                </ul>
            ) : (
                <p>No elevator data available.</p>
            )}
        </div>
    );
};

export default ElevatorInfo;

