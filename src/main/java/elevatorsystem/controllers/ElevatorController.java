package elevatorsystem.controllers;

import elevatorsystem.ElevatorSystem;
import elevatorsystem.model.Elevator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/elevators")
public class ElevatorController {
    private final ElevatorSystem elevatorSystem;

    @Autowired
    public ElevatorController(ElevatorSystem elevatorSystem) {
        this.elevatorSystem = elevatorSystem;
    }

    @GetMapping("/status")
    public List<Elevator> getStatus() {
        return elevatorSystem.sendStatusUpdate();
    }

    @GetMapping("/isRunning")
    public boolean isRunning() {
        return elevatorSystem.isRunning();
    }

    @PostMapping("/pickup")
    public void pickup(@RequestParam int pickupFloor, @RequestParam int targetFloor) {
        int direction = targetFloor > pickupFloor ? 1 : -1;
        elevatorSystem.pickup(pickupFloor, targetFloor, direction);
    }

    @PostMapping("/start")
    public void start() {
        elevatorSystem.runSimulation();
    }

    @PostMapping("/stop")
    public void stop() {
        elevatorSystem.stopSimulation();
    }
}

