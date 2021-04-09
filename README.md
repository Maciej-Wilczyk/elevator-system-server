## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Features](#features)
* [Basci overview](#basic-overview)

## General info
The Elevator System app is designed to simulate a building elevator control system. The project above presents the application server layer. You can find the client layer here: https://github.com/Maciej-Wilczyk/elevator-system-client
	
## Technologies
Project is created with:
* Java 11
* Spring-Boot
* SQLite
* Maven

## Setup
You need Java 11 (minimum) installed to run the application. Clone this repo to your desktop or dowland zip.
The easiest way to start the application with Maven (you must have it installed on your computer)
In the console go to the project folder and run the command: <br /> mvn spring-boot:run 


## Features
* The main feature of the application is to operate the elevator system which contains:
  * floor selection
  * call for an elevator to a requested floor
  * sorting selected floor (no FCFS, what matters is the direction of travel and whether the selected floor is already on its way)
  * update elevators status
  * make a simulation step
  * return the status of elevators
* database management:
  * set the number of elevators and floors
  * create, update, save and load data
* comunication with REST client
## Basic overview
After starting, the server checks if it has any configuration saved in the database. If not, it asks the client to provide such data. If the configuration is set, the server waits for requests from the client, do tasks, response and update database.
