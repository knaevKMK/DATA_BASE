package entities.hospital;

import entities.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Diagnose extends BaseEntity {
    private String name;
    private String comments;
    private Set<Patient> patients;

    public Diagnose() {
    }
@ManyToMany(mappedBy = "diagnoses")
    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "comments")
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
