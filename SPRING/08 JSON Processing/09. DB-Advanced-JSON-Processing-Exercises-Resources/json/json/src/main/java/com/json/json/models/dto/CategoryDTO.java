package com.json.json.models.dto;

import com.google.gson.annotations.Expose;

public class CategoryDTO {
    @Expose
    private String name;

    public CategoryDTO() {
    }

    public String getName() {
        return name;
    }

    public CategoryDTO setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
