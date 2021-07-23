package hiberspring.domain.dtos;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductNameClientsBranchNameDto {
    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "clients")
    private Integer clients;
    @XmlElement(name = "branch")
    private String branchName;

    public ProductNameClientsBranchNameDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getClients() {
        return clients;
    }

    public void setClients(Integer clients) {
        this.clients = clients;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
}
