package edu.java.hibernatetask.service.impl;

import edu.java.hibernatetask.entity.Training;
import edu.java.hibernatetask.repository.TrainingRepository;
import edu.java.hibernatetask.service.TrainingService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrainingServiceImpl implements TrainingService {

    private TrainingRepository trainingRepository;

    public TrainingServiceImpl(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @Override
    public Optional<Training> save(Training training) {
        return Optional.empty();
    }
}
