package entities.hospitalDatabase;

import entities.BaseEntite;

import javax.persistence.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "patients")
public class Patient extends BaseEntite {
    private String firstName;
    private String lastname;
    private String address;
    private String email;
    private LocalDate birthDate;
 //   private Image picture;
    private boolean medicalInsurance;

    private Set<Visitation> visitations;
    private Set<Diagnose> diagnoses;
    private Set<Medicament> medicaments;

    public Patient() {
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(joinColumns = @JoinColumn(referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(referencedColumnName = "id"))
    public Set<Diagnose> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(Set<Diagnose> diagnoses) {
        this.diagnoses = diagnoses;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(joinColumns = @JoinColumn (referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(referencedColumnName = "id"))
    public Set<Medicament> getMedicaments() {
        return medicaments;
    }

    public void setMedicaments(Set<Medicament> medicaments) {
        this.medicaments = medicaments;
    }

    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL)
    public Set<Visitation> getVisitations() {
        return visitations;
    }

    public void setVisitations(Set<Visitation> visitations) {
        this.visitations = visitations;
    }

    @Column(name = "medical_insurnce")
    public boolean isMedicalInsurance() {
        return medicalInsurance;
    }

    public void setMedicalInsurance(boolean medicalInsurance) {
        this.medicalInsurance = medicalInsurance;
    }




    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Column(name = "address", nullable = false)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "bith_date", nullable = false)
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

//    @Column(name = "picture")
//    public Image getPicture() {
//        return picture;
//    }
//
//    public void setPicture(Image picture) {
//        this.picture = picture;
//    }
}
