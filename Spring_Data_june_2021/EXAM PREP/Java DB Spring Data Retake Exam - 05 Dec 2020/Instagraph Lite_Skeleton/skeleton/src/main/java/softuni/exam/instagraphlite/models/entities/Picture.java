package softuni.exam.instagraphlite.models.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "pictures")
public class Picture extends BaseEntity {

    private String path;
    private Double size;
    private Set<User> users;
    private Set<Post> posts;

    public Picture() {
    }

    @OneToMany(mappedBy = "picture", fetch = FetchType.EAGER)
    public Set<Post> getPictures() {
        return posts;
    }

    public Picture setPictures(Set<Post> pictures) {
        this.posts = pictures;
        return this;
    }

    @OneToMany(mappedBy = "profilePicture", fetch = FetchType.EAGER)
    public Set<User> getUsers() {
        return users;
    }

    public Picture setUsers(Set<User> users) {
        this.users = users;
        return this;
    }

    @Column(unique = true)
    public String getPath() {
        return path;
    }

    public Picture setPath(String path) {
        this.path = path;
        return this;
    }

    @Column(nullable = false)
    public Double getSize() {
        return size;
    }

    public Picture setSize(Double size) {
        this.size = size;
        return this;
    }
}
