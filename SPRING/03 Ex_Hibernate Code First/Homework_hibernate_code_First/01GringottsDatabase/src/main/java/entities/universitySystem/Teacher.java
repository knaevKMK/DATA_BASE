package entities.universitySystem;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "teachers")
public class Teacher extends Person {
    private String email;
    private Double salaryPerHour;
    private Set<Course> courses;

    public Teacher() {
    }

    @OneToMany(mappedBy = "teacher", targetEntity = Course.class
            , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "salary_per_hour")
    public Double getSalaryPerHour() {
        return salaryPerHour;
    }

    public void setSalaryPerHour(Double salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }
}
