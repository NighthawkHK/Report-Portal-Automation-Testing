package entities;

import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public class User {

    private final String name;
    private final String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
