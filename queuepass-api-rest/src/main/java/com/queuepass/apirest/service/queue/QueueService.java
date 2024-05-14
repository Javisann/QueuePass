package com.queuepass.apirest.service.queue;

import com.queuepass.apirest.model.QueueModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QueueService {

    public List<QueueModel> findAll();

    public QueueModel save(QueueModel queueModel);

    public void deleteById(Long id);

}
