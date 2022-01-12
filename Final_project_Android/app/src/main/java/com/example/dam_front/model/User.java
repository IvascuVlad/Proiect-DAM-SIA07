package com.example.dam_front.model;

import java.util.ArrayList;
import java.util.List;

public class User implements Comparable<User>{
    private Integer userID;

    private String username;
    private String password;
    private String email;

    private Role role;

    private PersonalData personalData;

    private List<Studies> employeeStudies = new ArrayList<>();

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Integer getUserID() {
        return userID;
    }
    public void setUserID(Integer userID) {
        this.userID = userID;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public PersonalData getPersonalData() {
        return personalData;
    }
    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }
    public List <Studies> getEmployeeStudies() {
        return employeeStudies;
    }
    public void setEmployeeStudies(List <Studies> employeeStudies) {
        this.employeeStudies = employeeStudies;
    }

    public User() {
        super();
        this.username = null;
        this.password = null;
        this.email = null;
        this.role = null;
        this.personalData = null;
    }
    public User(String username) {
        super();
        this.username = username;
    }

    public User(String username, Role role) {
        super();
        this.username = username;
        this.role = role;
    }

    public User(String username, String password, String email, Role role) {
        super();
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public User(String username, String password, String email, Role role, PersonalData personalData, List <Studies> employeeStudies) {
        super();
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.personalData = personalData;
        this.employeeStudies = employeeStudies;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((userID == null) ? 0 : userID.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        User other = (User) obj;
        if (userID == null) {
            if (other.userID != null)
                return false;
        } else if (!userID.equals(other.userID))
            return false;
        if (username != other.username)
            return false;
        return true;
    }

    @Override
    public int compareTo(User other) {
        if (this.equals(other))
            return 0;
        return this.getUserID().compareTo(other.getUserID());
    }
    @Override
    public String toString() {
        String response = "User [userID=" + userID + ", username=" + username + ", password=" + password + ", email=" + email + ", role=" + role + ", personalData=" + personalData.toString() + ", studies=";
        for(Studies study:employeeStudies){
            response += study.toString();
        }
        response += "]";
        return response;
    }
}
