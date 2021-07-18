package com.json_xml.parse.models.entities.partUserProductCategoriy;

import com.json_xml.parse.models.entities.BaseEntity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {
    private int age;
    private String firstName;
    private String lastName;
    private Set<UserEntity> friends;
    private Set<ProductEntity> soldProducts;

    public UserEntity() {
    }

    @OneToMany(mappedBy = "seller",fetch = FetchType.EAGER)
    public Set<ProductEntity> getProducts() {
        return soldProducts;
    }

    public UserEntity setProducts(Set<ProductEntity> products) {
        this.soldProducts = products;
        return this;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "users_friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    public Set<UserEntity> getFriends() {
        return friends;
    }

    public UserEntity setFriends(Set<UserEntity> friends) {
        this.friends = friends;
        return this;
    }

    public int getAge() {
        return age;
    }

    public UserEntity setAge(int age) {
        this.age = age;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return age == that.age && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, firstName, lastName);
    }
}
