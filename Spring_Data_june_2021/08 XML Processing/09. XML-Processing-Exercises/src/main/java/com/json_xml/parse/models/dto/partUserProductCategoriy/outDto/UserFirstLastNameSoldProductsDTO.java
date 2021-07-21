package com.json_xml.parse.models.dto.partUserProductCategoriy.outDto;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "user ")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserFirstLastNameSoldProductsDTO {
    @XmlAttribute(name = "first-name")
    private String firstName;
    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlElement(name = "sold-products")
    private ProductRootDto soldProducts;

    public UserFirstLastNameSoldProductsDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UserFirstLastNameSoldProductsDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserFirstLastNameSoldProductsDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ProductRootDto getSoldProducts() {
        return soldProducts;
    }

    public UserFirstLastNameSoldProductsDTO setSoldProducts(ProductRootDto soldProducts) {
        this.soldProducts = soldProducts;
        return this;
    }
}
