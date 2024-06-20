
# Elevator System

## Project description 
The "Elevator Management System" project is a web application designed to manage 16 elevators in a building, optimizing their operation to ensure smooth and efficient transportation of users between floors. The application consists of a frontend built with React and a backend developed in Spring Boot, all automated and managed using Gradle.

Technologies:
 - Frontend: React
 - Backend: Spring Boot
 - Build Automation: Gradle


[Website](https://github.com/WiktorDybalski/Elevator_System/blob/main/demo1.png)

## Features:

### Elevator Management:

 - Real-time monitoring of the status of each of the 16 elevators.
 - Displaying the current location of each elevator and its status (idle, moving, maintenance, etc.).
 - Assigning tasks to elevators based on my own, simple traffic management algorithm 

### User Panel:

 - Intuitive user interface for quickly calling an elevator.
 - Option to select the destination floor and view current assignments for each elevator.

### Elevator algorithm:

My elevator assignment algorithm handles pickup requests by first filtering elevators that are already moving in the direction of the requested floor. It assigns the request to the closest elevator among those moving in the desired direction. If no such elevators are found, the algorithm selects the nearest idle elevator to handle the request. Each elevator has two PriorityQueues working independently to manage upward and downward requests.

### Usage:

Frontend and backend should be started on the separate terminal console

#### Cloning the Repository:

```sh
git clone https://github.com/WiktorDybalski/Elevator_System.git
```

```sh
cd Elevator_System
```

#### Starting the Backend:

```sh
.\gradlew.bat build
```

```sh
.\gradlew.bat run
```

or 

```sh
gradle build
```

```sh
gradle run
```

#### Starting the Frontend:

Open another terminale console

```sh
cd Elevator_System/client
```

```sh
npm install @vitejs/plugin-react --save-dev
```

```sh
npm install
```

```sh
npm run dev
```
Now take the link and paste it to the browser

## Demo

Check out my simple website with this demo video:
 [Website demo](https://www.loom.com/share/0662b6cd86a242169e37f086e84505b7)

## Author

- [Wiktor Dybalski](https://github.com/WiktorDybalski)

