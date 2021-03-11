package entities.bettDB;

import entities.BaseEntite;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "towns")
public class Town extends BaseEntite {
    private String name;
    private Country country;


}
