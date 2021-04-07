package com.server.spirngbootserver.Repository;

import com.server.spirngbootserver.model.SavedData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SavedDataRepository extends JpaRepository<SavedData,Integer> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM saved_data", nativeQuery = true)
     int deleteAllQuery();
}
