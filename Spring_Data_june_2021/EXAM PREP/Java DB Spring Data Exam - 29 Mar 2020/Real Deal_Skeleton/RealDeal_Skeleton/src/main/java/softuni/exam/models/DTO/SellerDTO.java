package softuni.exam.models.DTO;

import softuni.exam.models.enums.RatingEnum;

public class SellerDTO {
    private String firstName;
    private String lastName;
    private String email;
    private RatingEnum rating;
    private String town;

    public SellerDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public SellerDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public SellerDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public SellerDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public RatingEnum getRating() {
        return rating;
    }

    public SellerDTO setRating(RatingEnum rating) {
        this.rating = rating;
        return this;
    }

    public String getTown() {
        return town;
    }

    public SellerDTO setTown(String town) {
        this.town = town;
        return this;
    }
}
