package softuni.exam.domain.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "teams")
public class Team extends BaseEntity {

    private String name;
    private Picture picture;
    private Set<Player> players;

    public Team() {
    }

    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
    public Set<Player> getPlayers() {
        return players;
    }

    public Team setPlayers(Set<Player> players) {
        this.players = players;
        return this;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public Team setName(String name) {
        this.name = name;
        return this;
    }

    @ManyToOne
    @JoinColumn(name = "picture_id")
    public Picture getPicture() {
        return picture;
    }

    public Team setPicture(Picture picture) {
        this.picture = picture;
        return this;
    }
}
