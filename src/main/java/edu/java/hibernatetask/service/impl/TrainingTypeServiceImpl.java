package edu.java.hibernatetask.service.impl;

import edu.java.hibernatetask.entity.TrainingType;
import edu.java.hibernatetask.repository.DBException;
import edu.java.hibernatetask.repository.TrainingTypeRepository;
import edu.java.hibernatetask.service.ServiceException;
import edu.java.hibernatetask.service.TrainingTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TrainingTypeServiceImpl implements TrainingTypeService {
    private TrainingTypeRepository trainingTypeRepository;

    public TrainingTypeServiceImpl(TrainingTypeRepository trainingTypeRepository) {
        this.trainingTypeRepository = trainingTypeRepository;
    }

    @Override
    public Optional<TrainingType> getByName(String trainingType) throws ServiceException {
        try {
            return trainingTypeRepository.getByName(trainingType);
        } catch (DBException e) {
            throw new ServiceException("Fail to get from DB trainingType " + trainingType, e);
        }
    }

    @Override
    public Optional<TrainingType> getById(Long id) {
        return trainingTypeRepository.getById(id);
    }

    @Override
    public List<TrainingType> getAll() {
        return trainingTypeRepository.getAll();
    }
}
