package softuni.exam.instagraphlite.models.entities;


import javax.persistence.*;
import java.util.Set;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "pictures")
public class Picture extends BaseEntity {
    private String path;
    private double size;
    private Set<User> user;
    private Set<Post> posts;

    public Picture() {
    }

    @OneToMany(mappedBy = "picture",fetch = EAGER)
    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }



    @OneToMany(mappedBy = "profilePicture", fetch = EAGER)
    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    @Column(nullable = false, unique = true)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Column(nullable = false)
    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}
