package com.tumuhairwe.prep.genesys;

import java.awt.font.TextHitInfo;

//@Data
public class User {
    private String firstName;
    private String  lastName;

    public User(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
}

/**
 * String firstName;
 * String lastName;
 * String getFirstName() {return firstName;}
 * String getLastName() {return lastName;}
 *
 * }
 * List<User> users = new ArrayList<>();
 *
 * Given that users is populated with many Users with firstNames:
 * Using java streams return a list of User’s where the lastName starts with ‘D’
 */