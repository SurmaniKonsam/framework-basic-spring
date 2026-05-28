package com.jarvis.Beans;

public class MyRide {
    private String rideName;
    private int ccOfRide;
    private int mileageOfRide;
    private String kmPl;

    public void setRideName(String rideName) {
        this.rideName = rideName;
    }

    public void setCcOfRide(int cc){
        this.ccOfRide = cc;
    }

    public void setMileageOfRide(int mileageOfRide){
        this.mileageOfRide = mileageOfRide;
        this.kmPl = "Mileage of ride is : "+mileageOfRide;
    }

    @Override
    public String toString() {
        return "MyRide{" +
                "rideName='" + rideName + '\'' +
                ", ccOfRide=" + ccOfRide +
                ", mileageOfRide=" + mileageOfRide +
                ", kmPl='" + kmPl + '\'' +
                '}';
    }
}
