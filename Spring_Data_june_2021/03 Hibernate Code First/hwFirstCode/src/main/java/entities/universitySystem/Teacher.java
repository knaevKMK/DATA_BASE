package entities.universitySystem;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "teachers")
public class Teacher extends Person {

    private String email;
    private BigDecimal salaryPerHour;

    private Set<Course> courses;

    public Teacher() {
    }

    @OneToMany(mappedBy = "teacher", targetEntity = Course.class
            , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<Course> getCourses() {
        return courses;
    }


    @Column(name = "email", nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    @Column(name = "salary_per_hour", columnDefinition = "DECIMAL(10,3)")
    public BigDecimal getSalaryPerHour() {
        return salaryPerHour;
    }

    //setters


    public Teacher setEmail(String email) {
        this.email = email;
        return this;
    }

    public Teacher setSalaryPerHour(BigDecimal salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
        return this;
    }

    public Teacher setCourses(Set<Course> courses) {
        this.courses = courses;
        return this;
    }
}
