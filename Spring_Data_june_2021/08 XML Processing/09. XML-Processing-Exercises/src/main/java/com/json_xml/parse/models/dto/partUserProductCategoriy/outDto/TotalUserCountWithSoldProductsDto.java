package com.json_xml.parse.models.dto.partUserProductCategoriy.outDto;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class TotalUserCountWithSoldProductsDto {
    @XmlAttribute(name = "count")
    private int count;
    @Expose
    private List<UserFirstLastNameSoldProductsDTO> users;

    public TotalUserCountWithSoldProductsDto() {
    }

    public int getCount() {
        return count;
    }

    public TotalUserCountWithSoldProductsDto setCount(int count) {
        this.count = count;
        return this;
    }

    public List<UserFirstLastNameSoldProductsDTO> getUsers() {
        return users;
    }

    public TotalUserCountWithSoldProductsDto setUsers(List<UserFirstLastNameSoldProductsDTO> users) {
        this.users = users;
        return this;
    }
}
