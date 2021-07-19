package softuni.exam.instagraphlite.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post extends BaseEntity {

    private String caption;
    private User user;
    private Picture picture;

    public Post() {
    }

    @Column(nullable = false)
    public String getCaption() {
        return caption;
    }

    public Post setCaption(String caption) {
        this.caption = caption;
        return this;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public Post setUser(User user) {
        this.user = user;
        return this;
    }

    @ManyToOne
    @JoinColumn(name = "picture_id")
    public Picture getPicture() {
        return picture;
    }

    public Post setPicture(Picture picture) {
        this.picture = picture;
        return this;
    }
}
