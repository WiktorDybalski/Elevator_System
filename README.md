
# Elevator System

## Project description 
Projekt "System Zarządzania Windami" to aplikacja webowa, która umożliwia zarządzanie 16 windami w budynku, optymalizując ich pracę w celu zapewnienia płynnego i efektywnego transportu użytkowników między piętrami. Aplikacja składa się z frontendu zbudowanego przy użyciu React oraz backendu stworzonego w Spring Boot, a całość jest zautomatyzowana i zarządzana za pomocą Gradle.


Technologies:
 - Frontend: React
 - Backend: Spring Boot
 - Build Automation: Gradle

## Features:

### Elevator Management:

 - Real-time monitoring of the status of each of the 16 elevators.
 - Displaying the current location of each elevator and its status (idle, moving, maintenance, etc.).
 - Assigning tasks to elevators based on my own, simple traffic management algorithm 

### User Panel:

 - Intuitive user interface for quickly calling an elevator.
 - Option to select the destination floor and view current assignments for each elevator.

### Usage:

Frontend and backend should be started on the separate terminal console

#### Cloning the Repository:

git clone <https://github.com/WiktorDybalski/Elevator_System.git>

#### Starting the Backend:

.\gradlew.bat build

.\gradlew.bat bootRun

#### Starting the Frontend:

cd client

npm install

npm start


## Demo

![demo](https://github.com/WiktorDybalski/Elevator_System/blob/main/demo1.png)

## Author

- [Wiktor Dybalski](https://github.com/WiktorDybalski)

