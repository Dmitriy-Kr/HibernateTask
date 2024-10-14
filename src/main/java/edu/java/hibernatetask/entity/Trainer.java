package edu.java.hibernatetask.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Entity
public class Trainer{
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private TrainingType specialization;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToMany(mappedBy = "trainers")
    private List<Trainee> trainees = new ArrayList<>();

    public Trainer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TrainingType getSpecialization() {
        return specialization;
    }

    public void setSpecialization(TrainingType specialization) {
        this.specialization = specialization;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Trainee> getTrainees() {
        return trainees;
    }

    public void setTrainees(List<Trainee> trainees) {
        this.trainees = trainees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Trainer trainer = (Trainer) o;
        return getId().equals(trainer.getId()) && getSpecialization().equals(trainer.getSpecialization()) && getUser().equals(trainer.getUser()) && getTrainees().equals(trainer.getTrainees());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSpecialization(), getUser(), getTrainees());
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "id=" + id +
                ", specialization=" + specialization +
                ", user=" + user +
                ", trainees=" + trainees +
                '}';
    }
}
