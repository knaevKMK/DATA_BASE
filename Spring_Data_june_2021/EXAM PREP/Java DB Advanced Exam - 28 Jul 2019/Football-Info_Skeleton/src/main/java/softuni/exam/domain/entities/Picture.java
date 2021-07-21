package softuni.exam.domain.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "pictures")
public class Picture extends BaseEntity {
    private String url;
    private Set<Team> teams;
    private Set<Player> players;

    public Picture() {
    }

    @OneToMany(mappedBy = "picture", fetch = FetchType.EAGER)
    public Set<Player> getPlayers() {
        return players;
    }

    public Picture setPlayers(Set<Player> players) {
        this.players = players;
        return this;
    }

    @OneToMany(mappedBy = "picture", fetch = FetchType.EAGER)
    public Set<Team> getTeams() {
        return teams;
    }

    public Picture setTeams(Set<Team> teams) {
        this.teams = teams;
        return this;
    }

    @Column(nullable = false)
    public String getUrl() {
        return url;
    }

    public Picture setUrl(String url) {
        this.url = url;
        return this;
    }

}
