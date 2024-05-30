package com.queuepass.apirest.service.queue;

import com.queuepass.apirest.model.QueueModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface QueueService {

    public List<QueueModel> findAll();

    public QueueModel save(QueueModel queueModel);

    public void deleteById(Long id);

    public Integer positionCount(Long id);

    public Integer peopleInQueue();

    public Optional<QueueModel> findQueueModelByName(String name);

}
