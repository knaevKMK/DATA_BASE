package entities.hospitalDatabase;

import entities.BaseEntite;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "visitations")
public class Visitation extends BaseEntite {
    private LocalDateTime date;
    private String comment;
    private Patient patient;

    public Visitation() {
    }

    @ManyToOne(optional = false)
    @JoinColumn(referencedColumnName = "id")
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Column(name = "name", nullable = false)
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}
