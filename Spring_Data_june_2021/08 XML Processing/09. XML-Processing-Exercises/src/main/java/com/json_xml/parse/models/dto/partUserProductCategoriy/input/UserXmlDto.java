package com.json_xml.parse.models.dto.partUserProductCategoriy.input;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserXmlDto {
    @XmlAttribute(name = "first-name")
    private String firstName;
    @XmlAttribute(name = "last-name")
    private String lastName;
    @XmlAttribute(name = "age")
    private Integer age;

    public UserXmlDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UserXmlDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @NotNull(message = "Last name can not be null")
    @Size(min = 3, message = "Last name must be over 3 symbols")
    public String getLastName() {
        return lastName;
    }

    public UserXmlDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserXmlDto setAge(Integer age) {
        this.age = age;
        return this;
    }

    @Override
    public String toString() {
        return "UserInFromFileJsonDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
