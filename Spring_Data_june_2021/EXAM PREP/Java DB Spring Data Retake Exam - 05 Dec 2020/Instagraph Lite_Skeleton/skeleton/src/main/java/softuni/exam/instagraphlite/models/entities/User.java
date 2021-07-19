package softuni.exam.instagraphlite.models.entities;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private String username;
    private String password;
    private Picture profilePicture;
    private Set<Post> posts;

    public User() {
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    public Set<Post> getPosts() {
        return posts;
    }

    public User setPosts(Set<Post> posts) {
        this.posts = posts;
        return this;
    }

    @Column(unique = true)
    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    @ManyToOne
    @JoinColumn(name = "profile_picture_id")
    public Picture getProfilePicture() {
        return profilePicture;
    }

    public User setProfilePicture(Picture profilePicture) {
        this.profilePicture = profilePicture;
        return this;
    }
}
