package models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class User {

    private int id;
    private String name;
    private String address;

    private long marks;

    public User() {

    }

    public User(String name, String address, long marks) {
        this.name = name;
        this.address = address;
        this.marks = marks;


    }

    public User(int id, String name, String address, long marks) {
        this.name = name;
        this.address = address;
        this.marks = marks;
        this.id = id;

    }

}