package edu.java.hibernatetask.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;
@Entity
public class TrainingType {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String trainingType;

    public TrainingType() {
    }

    public TrainingType(String trainingType) {
        this.trainingType = trainingType;
    }

    public String getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(String trainingType) {
        this.trainingType = trainingType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        TrainingType that = (TrainingType) o;
        return getTrainingType().equals(that.getTrainingType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTrainingType());
    }

    @Override
    public String toString() {
        return "TrainingType{" +
                "trainingType='" + trainingType + '\'' +
                '}';
    }
}