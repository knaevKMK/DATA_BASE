package softuni.exam.models.entities;

import softuni.exam.models.enums.RatingEnum;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "sellers")
public class SellerEntity extends BaseEntity {

    private String firstName;
    private String lastName;
    private String email;
    private RatingEnum rating;
    private String town;

    public SellerEntity() {
    }

    @Column(name = "first_name", columnDefinition = "VARCHAR (20)")
    public String getFirstName() {
        return firstName;
    }

    public SellerEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @Column(name = "last_name", columnDefinition = "VARCHAR (20)")
    public String getLastName() {
        return lastName;
    }

    public SellerEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @Email(regexp = "[a-zA-Z0-9]+[._a-zA-Z0-9!#$%&'*+-/=?^_`{|}~]*[a-zA-Z]*@[a-zA-Z0-9]{2,8}.[a-zA-Z.]{2,6}")
    public String getEmail() {
        return email;
    }

    public SellerEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    @Enumerated(EnumType.STRING)
    public RatingEnum getRating() {
        return rating;
    }

    public SellerEntity setRating(RatingEnum rating) {
        this.rating = rating;
        return this;
    }

    @Column(nullable = false)
    public String getTown() {
        return town;
    }

    public SellerEntity setTown(String town) {
        this.town = town;
        return this;
    }


}
