package com.json_xml.parse.models.dto.outDto;

import com.google.gson.annotations.Expose;

import java.util.List;
import java.util.Set;

public class TotalUserCountWithSoldProductsDto {
    @Expose
    private int usersCount;
    @Expose
    private List<UserFirstLastNameAgeSoldProductsDTO> users;

    public TotalUserCountWithSoldProductsDto() {
    }

    public int getUsersCount() {
        return usersCount;
    }

    public TotalUserCountWithSoldProductsDto setUsersCount(int usersCount) {
        this.usersCount = usersCount;
        return this;
    }

    public List<UserFirstLastNameAgeSoldProductsDTO> getUsers() {
        return users;
    }

    public TotalUserCountWithSoldProductsDto setUsers(List<UserFirstLastNameAgeSoldProductsDTO> users) {
        this.users = users;
        return this;
    }
}
