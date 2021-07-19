package softuni.exam.models.dto.input;

import com.google.gson.annotations.Expose;
import softuni.exam.models.entities.TownEntity;

import javax.validation.constraints.Email;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class PassengerDto {
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private int age;
    @Expose
    private String phoneNumber;
    @Expose
    private String email;
    @Expose
    private String town;

    public PassengerDto() {
    }
@Size(min = 2)
    public String getFirstName() {
        return firstName;
    }

    public PassengerDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }
    @Size(min = 2)
    public String getLastName() {
        return lastName;
    }

    public PassengerDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
@Positive
    public int getAge() {
        return age;
    }

    public PassengerDto setAge(int age) {
        this.age = age;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public PassengerDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
    @Email(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}")
    public String getEmail() {
        return email;
    }

    public PassengerDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getTown() {
        return town;
    }

    public PassengerDto setTown(String town) {
        this.town = town;
        return this;
    }
}
