package DAM.models;

import javafx.geometry.Pos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Contract implements Comparable<Contract>, Serializable{
    //@Min(1) @NotNull(message = "Contract ID is required!")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer contractID;

//    @NotNull(message = "User is required!")
//    @Size(min=1, message = "Contract must have an assigned user!")
    @OneToOne
    private User user;

    @OneToMany
    private List<Position> positions = new ArrayList<>();

    @Transient
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
    public int compareTo(Contract o) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String toString() {
        String response = "Contract [contractID=" + contractID + ", user=" + user.toString() + ", postions=";
        for(Position position:positions){
            response += position.toString();
        }
        response += ", with =" + positionsCount + " positions]";
        return response;
    }
}
