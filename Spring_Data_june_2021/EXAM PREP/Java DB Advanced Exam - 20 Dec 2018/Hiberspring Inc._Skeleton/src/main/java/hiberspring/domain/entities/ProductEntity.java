package hiberspring.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class ProductEntity extends  BaseEntity{
    private String name;
    private Integer clients;
    private BranchEntity branch;

    public ProductEntity() {
    }
    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(nullable = false)
    public Integer getClients() {
        return clients;
    }

    public void setClients(Integer clients) {
        this.clients = clients;
    }
@ManyToOne
@JoinColumn(name = "branch_id")
    public BranchEntity getBranch() {
        return branch;
    }

    public void setBranch(BranchEntity branchEntity) {
        this.branch = branchEntity;
    }
}
