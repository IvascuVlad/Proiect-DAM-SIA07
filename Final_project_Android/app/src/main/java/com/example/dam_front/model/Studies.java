package com.example.dam_front.model;

public class Studies {
    private Integer studiesID;

    private String institution;
    private String profile;
    private Integer baseSalary;

    public String getInstitution() {
        return institution;
    }
    public void setInstitution(String institution) {
        this.institution = institution;
    }
    public String getProfile() {
        return profile;
    }
    public void setProfile(String profile) {
        this.profile = profile;
    }
    public Integer getstudiesID() {
        return studiesID;
    }
    public void setstudiesID(Integer studiesID) {
        this.studiesID = studiesID;
    }
    public Integer getBaseSalary() {
        return baseSalary;
    }
    public void setBaseSalary(Integer baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Studies() {
        super();
        this.institution = null;
        this.profile = null;
        this.baseSalary = null;
    }

    public Studies( String institution, String profile) {
        super();
        this.institution = institution;
        this.profile = profile;
    }

    public Studies( String institution, String profile, Integer baseSalary) {
        super();
        this.institution = institution;
        this.profile = profile;
        this.baseSalary = baseSalary;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((studiesID == null) ? 0 : studiesID.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        Studies other = (Studies) obj;
        if (studiesID == null) {
            if (other.studiesID != null)
                return false;
        } else if (!studiesID.equals(other.studiesID))
            return false;
        if (institution != other.institution && profile != other.profile)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Study [studiesID=" + studiesID + ", institution=" + institution
                + ", profile=" + profile + ", baseSalary=" + baseSalary + "]";
    }
}
