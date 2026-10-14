# TRACEON

## An Infrastructure Independent Disaster Survivor Tracking and Rescue Coordination System

TRACEON is a disaster-response system designed to support survivor tracking, emergency alert management, communication-node monitoring, route monitoring, and rescue coordination even when conventional communication infrastructure is unavailable or disrupted.

The system uses Java, SQLite, and a LoRa-based communication concept to provide a centralized platform for managing emergency information and coordinating rescue activities.

---

## Problem Statement

During natural disasters and emergency situations, mobile networks and other communication infrastructure may become unavailable or unreliable.

This creates difficulties in:

- Sending emergency alerts
- Identifying affected families
- Monitoring communication nodes
- Tracking rescue operations
- Managing emergency routes
- Coordinating rescue teams

TRACEON addresses these challenges through an infrastructure-independent emergency communication and rescue coordination approach.

---

## Objectives

- Provide emergency SOS and HELP alert management.
- Identify households using Family ID and House ID.
- Monitor communication nodes.
- Monitor emergency routes.
- Track rescue cases and their progress.
- Store emergency and rescue information using SQLite.
- Provide a centralized web-based dashboard.
- Support LoRa-based communication for infrastructure-independent operation.

---

## Key Features

- Emergency SOS alerts
- HELP assistance requests
- Family and household identification
- Communication node monitoring
- Route status monitoring
- Rescue case tracking
- Centralized dashboard
- Emergency alert management
- SQLite database integration
- Java backend APIs
- Web-based monitoring interface

---

## System Architecture

```text
Emergency Input
       |
       v
Family / House Identification
       |
       v
LoRa Communication
       |
       v
Communication Nodes
       |
       v
Java Backend
       |
       v
SQLite Database
       |
       v
Web Dashboard
       |
       v
Rescue Coordination

Technology Stack
Frontend
- HTML
- CSS
- JavaScript
Backend
- Java
- Java HTTP Server
- REST-style APIs
Database
- SQLite
- JDBC
Communication
- LoRa-based communication concept
Development Tools
- Visual Studio Code
- Java JDK
- Git
- GitHub
Project Modules
1. Dashboard
Provides a centralized overview of:
- Emergency alerts
- Registered families
- Communication nodes
- Routes
- Rescue cases
2. Emergency Alert Management
Manages emergency alerts received from disaster-affected households.
Alert types include:
- SOS – High Priority
- HELP – Medium Priority
3. Family Management
Stores household information using:
- Family ID
- House ID
- Family Name
- Location
4. Communication Node Monitoring
Monitors communication nodes and their operational status.
The system can display:
- Node ID
- Node status
- Last message
- Messages forwarded
5. Route Monitoring
Maintains emergency communication routes between nodes.
Route information includes:
- Route ID
- Source node
- Destination node
- Distance
- Route status
6. Rescue Tracking
Tracks rescue cases and their progress.
Information includes:
- Case ID
- Alert ID
- Family ID
- Rescue team
- Rescue status
- Progress
7. Reports
Provides summarized information related to:
- Emergency alerts
- Families
- Communication nodes
- Routes
- Rescue cases
Emergency Priority
TRACEON currently follows a rule-based priority approach.
Alert Type	Priority
SOS	High
HELP	Medium


The system uses predefined rules instead of AI/ML-based prediction.
Database
TRACEON uses SQLite for local data storage.
The main database tables are:
- family
- emergency_alert
- communication_node
- route
- rescue_case
Database connectivity is implemented using JDBC.
Java Concepts Used
The project demonstrates important Java programming concepts including:
- Classes and Objects
- Encapsulation
- Inheritance
- Polymorphism
- Exception Handling
- JDBC
- Multithreading
- Packages
- Interfaces / Service-based design
TRACEON-JAVA
│
├── src
│   ├── model
│   │   ├── CommunicationNode.java
│   │   ├── EmergencyAlert.java
│   │   ├── Family.java
│   │   ├── HelpAlert.java
│   │   ├── RescueCase.java
│   │   ├── Route.java
│   │   └── SOSAlert.java
│   │
│   ├── database
│   │   ├── DatabaseConnection.java
│   │   ├── DatabaseSetup.java
│   │   └── DAO classes
│   │
│   └── main
│       └── ApiServer.java
│
├── Main.java
├── README.md
└── .gitignore
Web Pages
The system provides the following web pages:
- Dashboard
- Alerts
- Families
- Network
- Routes
- Rescue
- Reports
The Java backend provides API endpoints for system data and emergency alert processing.
API Endpoints
Endpoint	Purpose
/api/status	System status
/api/alerts	Emergency alerts
/api/family	Family information
/api/nodes	Communication node information
/api/routes	Route information
/api/rescue	Rescue information
/api/sos	SOS alert submission
## How to Run

### 1. Clone the Repository

```bash
git clone https://github.com/swarna033/TRACEON-JAVA.git
cd TRACEON-JAVA
2. Configure SQLite JDBC
Download the SQLite JDBC driver and place the .jar file in the project root directory.
Required file:
sqlite-jdbc-3.53.4.0.jar
3. Compile the Java Backend
Open the terminal in the project root directory and run:
javac -cp ".;sqlite-jdbc-3.53.4.0.jar" -d . src\database\DatabaseConnection.java src\main\ApiServer.java
. Start the Backend
Run:
java -cp ".;sqlite-jdbc-3.53.4.0.jar" main.ApiServer
If the server starts successfully, the TRACEON backend will run on port 8080.
5. Open the Dashboard
Open a web browser and visit:
http://localhost:8080/
Other Pages
- Dashboard: http://localhost:8080/
- Alerts: http://localhost:8080/alerts
- Families: http://localhost:8080/families
- Network: http://localhost:8080/network
- Routes: http://localhost:8080/routes
- Rescue: http://localhost:8080/rescue
- Reports: http://localhost:8080/reports
## System Screenshots

### Dashboard

![TRACEON Dashboard](screenshots/01-dashboard.png)

### Emergency Alerts

![Emergency Alerts](screenshots/02-alerts.png)

### Family Management

![Family Management](screenshots/03-families.png)

### Node Network

![Node Network](screenshots/04-node-network.png)

### Route Monitoring

![Route Monitoring](screenshots/05-routes.png)

### Rescue Tracking

![Rescue Tracking](screenshots/06-rescue.png)

### System Reports

![System Reports](screenshots/07-reports.png)