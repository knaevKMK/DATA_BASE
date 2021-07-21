package com.json_xml.parse.models.dto.partUserProductCategoriy.outDto;


import javax.xml.bind.annotation.*;

@XmlRootElement(name = "user ")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserFirstLastNameSoldProducts2DTO {
    @XmlAttribute(name = "first-name")
    private String firstName;
    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlElement(name = "sold-products")
    private ProductRoot2Dto soldProducts;

    public UserFirstLastNameSoldProducts2DTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UserFirstLastNameSoldProducts2DTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserFirstLastNameSoldProducts2DTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ProductRoot2Dto getSoldProducts() {
        return soldProducts;
    }

    public UserFirstLastNameSoldProducts2DTO setSoldProducts(ProductRoot2Dto soldProducts) {
        this.soldProducts = soldProducts;
        return this;
    }
}
