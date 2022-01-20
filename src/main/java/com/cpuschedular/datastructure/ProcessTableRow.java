package main.java.com.cpuschedular.datastructure;

public class ProcessTableRow {
    private float arrival, burst;
    String PID;

    public ProcessTableRow(String PID, float arrival, float burst ) {
        this.arrival = arrival;
        this.burst = burst;
        this.PID = PID;
    }

    public float getArrival() {
        return arrival;
    }

    public void setArrival(float arrival) {
        this.arrival = arrival;
    }

    public float getBurst() {
        return burst;
    }

    public void setBurst(float burst) {
        this.burst = burst;
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }
}