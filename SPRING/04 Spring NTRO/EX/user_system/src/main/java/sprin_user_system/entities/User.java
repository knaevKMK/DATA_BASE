package sprin_user_system.entities;

import org.hibernate.validator.constraints.Length;
import sprin_user_system.annotations.email.Email;
import sprin_user_system.annotations.password.Password;
import sprin_user_system.constants.TextConstants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private String username;
    private String password;
    private String email;
    private LocalDateTime registeredOn;
    private LocalDateTime lastTimeLoggedIn;
    private String firstName;
    private String lastName;
    private int age;
    private Town bornInTown;
    private Town liveInTown;
    private Set<User> friends;
    private Set<Album> albums;
    private boolean isDeleted;

    public User() {
    }

    @Column(nullable = false, unique = true)
    @Length(min = 4, max = 30, message = TextConstants.USERNAME_INCORRECT_LENGTH)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(nullable = false)
    @Password(minLength = 6, maxLength = 50, containsDigit = true, containsLowerCase = true, containsUpperCase = true, containsSpecialSymbols = true)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(nullable = false, unique = true)
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDateTime registeredOn) {
        this.registeredOn = registeredOn;
    }

    public LocalDateTime getLastTimeLoggedIn() {
        return lastTimeLoggedIn;
    }

    public void setLastTimeLoggedIn(LocalDateTime lastTimeLoggedIn) {
        this.lastTimeLoggedIn = lastTimeLoggedIn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Min(value = 1, message = TextConstants.AGE_TOO_LOW)
    @Max(value = 120, message = TextConstants.AGE_TOO_HIGH)
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Town getBornInTown() {
        return bornInTown;
    }

    public void setBornInTown(Town bornInTown) {
        this.bornInTown = bornInTown;
    }

    public Town getLiveInTown() {
        return liveInTown;
    }

    public void setLiveInTown(Town liveInTown) {
        this.liveInTown = liveInTown;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
