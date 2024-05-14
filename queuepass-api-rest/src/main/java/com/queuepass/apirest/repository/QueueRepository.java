package com.queuepass.apirest.repository;

import com.queuepass.apirest.model.QueueModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueueRepository extends JpaRepository<QueueModel, Long> {
}
