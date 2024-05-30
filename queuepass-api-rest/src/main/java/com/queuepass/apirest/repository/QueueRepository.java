package com.queuepass.apirest.repository;

import com.queuepass.apirest.model.QueueModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QueueRepository extends JpaRepository<QueueModel, Long> {

    @Query(value = """
            SELECT count(*) + 1 as position
            FROM queue
            where id < :id""",
            nativeQuery = true)
    public Integer positionCount(@Param("id") Long id);

    @Query(value = """
            SELECT count(*) as people_in_queue
            FROM queue""",
            nativeQuery = true)
    public Integer peopleInQueue();

    public Optional<QueueModel> findQueueModelByName(String name);
}
