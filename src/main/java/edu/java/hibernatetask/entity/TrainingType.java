package edu.java.hibernatetask.entity;

import javax.persistence.*;

import java.util.Objects;
@Entity
public class TrainingType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trainingtype_id")
    private Long id;
    @Column
    private String trainingType;

    public TrainingType() {
    }

    public TrainingType(String trainingType) {
        this.trainingType = trainingType;
    }

    public TrainingType(Long id, String trainingType) {
        this.id = id;
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
