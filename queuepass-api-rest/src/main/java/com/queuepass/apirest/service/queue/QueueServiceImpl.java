package com.queuepass.apirest.service.queue;

import com.queuepass.apirest.model.QueueModel;
import com.queuepass.apirest.repository.QueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueueServiceImpl implements QueueService{

    @Autowired
    private QueueRepository queueRepository;

    @Override
    public List<QueueModel> findAll() {
        return this.queueRepository.findAll();
    }

    @Override
    public QueueModel save(QueueModel queueModel) {
        return this.queueRepository.save(queueModel);
    }

    @Override
    public void deleteById(Long id) {
        this.queueRepository.deleteById(id);
    }

    @Override
    public Integer positionCount(Long id) {
        if(!this.queueRepository.existsById(id)){
            return 0;
        }
        return this.queueRepository.positionCount(id);
    }

    @Override
    public Integer peopleInQueue() {
        return this.queueRepository.peopleInQueue();
    }
}