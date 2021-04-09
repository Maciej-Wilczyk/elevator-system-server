## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Features](#features)

## General info
The Elevator System app is designed to simulate a building elevator control system. The above project presents the application server layer. You can find the client layer here: "link"
	
## Technologies
Project is created with:
* Java 11
* Spring-Boot
* SQLite
* Maven

## Setup
You need Java 11 (minimum) installed to run the application.
The easiest way to start the application with Maven (you must have it installed on your computer)
In the console go to the project folder and run the command:
mvn spring-boot:run

Drugim sposobem będzie 

## Features
* The main feature of the application is to operate the elevator system which contains:
** floor selection
** call for an elevator to a requested floor
** sorting selected floor (no FCFS, what matters is the direction of travel and whether the selected floor is already on its way)
** update elevators status
** make a simulation step
** return the status of elevators
* database management
** set the number of elevators and floors
** create, update, save and load data
* comunication with REST client
