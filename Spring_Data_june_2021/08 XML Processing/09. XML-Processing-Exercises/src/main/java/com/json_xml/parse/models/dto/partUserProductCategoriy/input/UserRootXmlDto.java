package com.json_xml.parse.models.dto.partUserProductCategoriy.input;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserRootXmlDto {

    @XmlElement(name = "user")
    private Set<UserXmlDto> userXmlDtos;

    public UserRootXmlDto() {
    }

    public Set<UserXmlDto> getUserXmlDtos() {
        return userXmlDtos;
    }

    public UserRootXmlDto setUserXmlDtos(Set<UserXmlDto> userXmlDtos) {
        this.userXmlDtos = userXmlDtos;
        return this;
    }
}
