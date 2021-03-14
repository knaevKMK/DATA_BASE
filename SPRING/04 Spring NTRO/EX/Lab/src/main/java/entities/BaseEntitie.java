package entities;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseEntitie {
    private Long id;

    BaseEntitie(){
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
