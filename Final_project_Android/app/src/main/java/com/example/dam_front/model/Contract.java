package com.example.dam_front.model;

import java.util.ArrayList;
import java.util.List;

public class Contract {
    private Integer contractID;

    private User user;

    private List<Position> positions = new ArrayList<>();

    protected Integer positionsCount = 0;

    public Contract(){
        super();
        this.user = new User();
    }

    public Contract(User user){
        super();
        this.user = user;
    }

    public Contract(User user, List<Position> positions){
        super();
        this.user = user;
        this.positions = positions;
        this.positionsCount = positions.size();
    }

    public Integer getContractID() {
        return contractID;
    }
    public void setContractID(Integer contractID) {
        this.contractID = contractID;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) { this.user = user; }
    public List<Position> getPositions() {
        return positions;
    }
    public void setReleases(List<Position> positions) {
        this.positions = positions;
    }
    public Integer getPositionsCount() {
        return positionsCount;
    }
    public void setReleaseCount(Integer positionsCount) {
        this.positionsCount = positionsCount;
    }

    @Override
    public String toString() {
        String response = "Contract [ contractID=" + contractID + ", user=" + user.toString() + ", postions=";
        for(Position position:positions){
            response += position.toString();
        }
        response += ", with =" + positionsCount + " positions]";
        return response;
    }
}
