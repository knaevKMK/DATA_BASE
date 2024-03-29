package entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@MappedSuperclass
public abstract class BaseEntite {
    private String id;

    public BaseEntite() {
    }

    @Id
    @GeneratedValue(generator = "uuid-string")
    @GenericGenerator(name = "uuid-string", strategy = "org.hibernate.id.UUIDGenerator")
    @Column
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
