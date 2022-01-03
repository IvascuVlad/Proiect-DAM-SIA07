package DAM.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class PersonalData implements Comparable<PersonalData>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer dataID;

    private String firstName;
    private String lastName;
    private String phoneNo;
    private String address;

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public Integer getDataID() {
        return dataID;
    }
    public void setDataID(Integer dataID) {
        this.dataID = dataID;
    }
    public String getPhoneNo() {
        return phoneNo;
    }
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public PersonalData() {
        super();
        this.firstName = null;
        this.lastName = null;
        this.phoneNo = null;
        this.address = null;
    }
    public PersonalData( String firstName, String lastName) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public PersonalData(String firstName, String lastName, String phoneNo, String address) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
        this.address = address;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((dataID == null) ? 0 : dataID.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        PersonalData other = (PersonalData) obj;
        if (dataID == null) {
            if (other.dataID != null)
                return false;
        } else if (!dataID.equals(other.dataID))
            return false;
        if (firstName != other.firstName && lastName != other.lastName)
            return false;
        return true;
    }

    @Override
    public int compareTo(PersonalData other) {
        if (this.equals(other))
            return 0;
        return this.getDataID().compareTo(other.getDataID());
    }
    @Override
    public String toString() {
        return "Personal Data [firstName=" + firstName + ", lastName=" + lastName
                + ", phoneNo=" + phoneNo + ", address=" + address + "]";
    }

}
