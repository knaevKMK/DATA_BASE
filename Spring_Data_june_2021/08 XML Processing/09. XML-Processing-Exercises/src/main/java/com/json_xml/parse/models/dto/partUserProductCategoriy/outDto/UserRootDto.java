package com.json_xml.parse.models.dto.partUserProductCategoriy.outDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name="users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserRootDto {

    @XmlElement(name = "user")
    private Set<UserFirstLastNameSoldProductsDTO> userDtos;

    public UserRootDto() {
    }

    public Set<UserFirstLastNameSoldProductsDTO> getUserDtos() {
        return userDtos;
    }

    public UserRootDto setUserDtos(Set<UserFirstLastNameSoldProductsDTO> userDtos) {
        this.userDtos = userDtos;
        return this;
    }
}
