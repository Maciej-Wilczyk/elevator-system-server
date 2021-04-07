package com.server.spirngbootserver.Repository;

import com.server.spirngbootserver.model.ElevatorSystemConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ElevatorSystemConfigRepository extends JpaRepository<ElevatorSystemConfig, Integer> {
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM elevator_system_config", nativeQuery = true)
    int deleteAllQuery();
}
