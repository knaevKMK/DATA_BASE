package entities.universitySystem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student extends Person {

    private Double averageGrade;
    private int attendance;

    public Student() {
    }

    @Column(name = "average_grade")
    public Double getAverageGrade() {
        return averageGrade;
    }

    @Column
    public int getAttendance() {
        return attendance;
    }
    //setters
    public Student setAverageGrade(Double averageGrade) {
        this.averageGrade = averageGrade;
        return this;
    }

    public Student setAttendance(int attendance) {
        this.attendance = attendance;
        return this;
    }
}
