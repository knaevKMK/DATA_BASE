package com.json_xml.parse.models.dto.input;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Size;

public class CategoryInFromJsonDto {
    @Expose
    private String name;

    public CategoryInFromJsonDto() {
    }
@Size(min = 3, max = 15, message = "Name must contains between 3 and 15 symbols")
    public String getName() {
        return name;
    }

    public CategoryInFromJsonDto setName(String name) {
        this.name = name;
        return this;
    }
}
