package DAM.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class Position implements Comparable<Position>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer positionID;

    private String positionName;
    private String positionDescription;
    private Integer positionRate;

    public String getPositionName() {
        return positionName;
    }
    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
    public String getPositionDescription() {
        return positionDescription;
    }
    public void setPositionDescription(String positionDescription) {
        this.positionDescription = positionDescription;
    }
    public Integer getPositionID() {
        return positionID;
    }
    public void setPositionID(Integer positionID) {
        this.positionID = positionID;
    }
    public Integer getPositionRate() {
        return positionRate;
    }
    public void setPositionRate(Integer positionRate) {
        this.positionRate = positionRate;
    }

    public Position() {
        super();
        this.positionName = null;
        this.positionDescription = null;
        this.positionRate = null;
    }
    public Position(String positionName) {
        super();
        this.positionName = positionName;
    }
    public Position( String positionName, String positionDescription) {
        super();
        this.positionName = positionName;
        this.positionDescription = positionDescription;
    }


    public Position(Integer positionID, String positionName, String positionDescription, Integer positionRate) {
        super();
        this.positionName = positionName;
        this.positionDescription = positionDescription;
        this.positionRate = positionRate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((positionID == null) ? 0 : positionID.hashCode());
        result = prime * result + ((positionRate == null) ? 0 : positionRate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
//		if (getClass() != obj.getClass())
//			return false;
        Position other = (Position) obj;
        if (positionID == null) {
            if (other.positionID != null)
                return false;
        } else if (!positionID.equals(other.positionID))
            return false;
        if (positionName != other.positionName)
            return false;
        return true;
    }

    @Override
    public int compareTo(Position other) {
        if (this.equals(other))
            return 0;
        return this.getPositionID().compareTo(other.getPositionID());
    }
    @Override
    public String toString() {
        return "Position [positionID=" + positionID + ", positionName=" + positionName
                + ", positionDescription=" + positionDescription + ", positionRate=" + positionRate + "]";
    }
}
