package com.digipolitan.sample.retrofit.domain.user.model;

import com.digipolitan.sample.retrofit.platform.gson.Exclude;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Julien BACZYNSKI http://www.digipolitan.com on 10/04/2017.
 */

public class User {
    @SerializedName("_id")
    @Exclude(deserialize = false)
    private String id;
    private String login;
    @Exclude(serialize = false)
    private String password;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("first_name")
    private String firstName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public static JsonSerializer<User> getGsonSerializer() {
        return null;
    }

    public static JsonDeserializer<User> getGsonDeserializer() {
        return null;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}
