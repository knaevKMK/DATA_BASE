package softuni.exam.models.dto;


import org.hibernate.validator.constraints.Length;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "seller")
@XmlAccessorType(XmlAccessType.FIELD)
public class SellerDto {

    @XmlElement(name = "first-name")
    private String firstName;
    @XmlElement(name = "last-name")
    private String lastName;
    @XmlElement(name = "email")
    private String email;
    @XmlElement
    private String rating;
    @XmlElement
    private String town;

    public SellerDto() {
    }

    @Length(min = 2, max = 20)
    public String getFirstName() {
        return firstName;
    }

    public SellerDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @Length(min = 2, max = 20)
    public String getLastName() {
        return lastName;
    }

    public SellerDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }


   @Email(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}")
    public String getEmail() {
        return email;
    }

    public SellerDto setEmail(String email) {
        this.email = email;
        return this;
    }


    public String getRating() {
        return rating;
    }

    public SellerDto setRating(String rating) {
        this.rating = rating;
        return this;
    }

    @NotNull
    public String getTown() {
        return town;
    }

    public SellerDto setTown(String town) {
        this.town = town;
        return this;
    }

    @Override
    public String toString() {
        return "SellerDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", rating='" + rating + '\'' +
                ", town='" + town + '\'' +
                '}';
    }
}
