package edu.java.hibernatetask.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Trainee {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private Date dateOfBirth;
    @Column
    private String address;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToMany
    @JoinTable()
    private List<Trainer> trainers = new ArrayList<>();


    public Trainee() {
    }

    public Trainee(Long id, Date dateOfBirth, String address) {
        this.id = id;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Trainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(List<Trainer> trainers) {
        this.trainers = trainers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Trainee trainee = (Trainee) o;
        return getId().equals(trainee.getId()) && getDateOfBirth().equals(trainee.getDateOfBirth()) && getAddress().equals(trainee.getAddress()) && getUser().equals(trainee.getUser()) && getTrainers().equals(trainee.getTrainers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDateOfBirth(), getAddress(), getUser(), getTrainers());
    }

    @Override
    public String toString() {
        return "Trainee{" +
                "id=" + id +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                ", user=" + user +
                ", trainers=" + trainers +
                '}';
    }
}