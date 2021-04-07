package com.server.spirngbootserver.model;

import lombok.Data;
import org.hibernate.annotations.GeneratorType;
import org.springframework.cglib.core.GeneratorStrategy;

import javax.persistence.*;

@Entity
@Table(name = "elevator_system_config")
@Data
public class ElevatorSystemConfig {

    @Id
    int id;

    @Column
    int numberOfFloors;

    @Column
    int numberOfElevators;

}
