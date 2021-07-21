package com.json_xml.parse.models.dto.partUserProductCategoriy.outDto;

import javax.xml.bind.annotation.*;
import java.util.Set;

@XmlRootElement(name="users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserRoot2Dto {
@XmlAttribute(name="count")
private int count;
    @XmlElement(name = "user")
    private Set<UserFirstLastNameSoldProducts2DTO> userDtos;

    public UserRoot2Dto() {
    }

    public int getCount() {
        return count;
    }

    public UserRoot2Dto setCount(int count) {
        this.count = count;
        return this;
    }

    public Set<UserFirstLastNameSoldProducts2DTO> getUserDtos() {
        return userDtos;
    }

    public UserRoot2Dto setUserDtos(Set<UserFirstLastNameSoldProducts2DTO> userDtos) {
        this.userDtos = userDtos;
        return this;
    }
}
