package com.json_xml.parse.models.dto.outDto;

import com.google.gson.annotations.Expose;

import java.util.Set;

public class UserFirstLastNameAgeSoldProductsDTO {
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private int age;
    @Expose
    private SoldProductCountAndListDto soldProducts;

    public UserFirstLastNameAgeSoldProductsDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UserFirstLastNameAgeSoldProductsDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserFirstLastNameAgeSoldProductsDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public int getAge() {
        return age;
    }

    public UserFirstLastNameAgeSoldProductsDTO setAge(int age) {
        this.age = age;
        return this;
    }

    public SoldProductCountAndListDto getSoldProducts() {
        return soldProducts;
    }

    public UserFirstLastNameAgeSoldProductsDTO setSoldProducts(SoldProductCountAndListDto soldProducts) {
        this.soldProducts = soldProducts;
        return this;
    }
}
