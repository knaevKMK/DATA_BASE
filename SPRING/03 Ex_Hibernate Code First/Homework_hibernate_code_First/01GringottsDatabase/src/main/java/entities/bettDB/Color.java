package entities.bettDB;

import entities.BaseEntite;

import javax.persistence.*;

@Entity
@Table(name = "colors")
public class Color extends BaseEntite {
    private String name;

    public Color() {
    }

    @Column(name = "color_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
