package com.nav.aconex.analyser.result;

public class IndividualIntervalAnalysisResult {
    private String rowDescription;
    private int rowCount;
    private int vehicleCountInSouthDirection;
    private int vehicleCountInNorthDirection;
    private int totalCount;
    private String intervalStartTime;
    private String intervalEndTime;


    public IndividualIntervalAnalysisResult(String rowDescription, int
            rowCount, int vehicleCountInSouthDirection, int vehicleCountInNorthDirection, int totalCount, String intervalStartTime, String intervalEndTime) {
        this.rowDescription = rowDescription;
        this.rowCount = rowCount;
        this.vehicleCountInSouthDirection = vehicleCountInSouthDirection;
        this.vehicleCountInNorthDirection = vehicleCountInNorthDirection;
        this.totalCount = totalCount;
        this.intervalStartTime = intervalStartTime;
        this.intervalEndTime = intervalEndTime;
    }

    public String getRowDescription() {
        return rowDescription;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getVehicleCountInSouthDirection() {
        return vehicleCountInSouthDirection;
    }

    public int getVehicleCountInNorthDirection() {
        return vehicleCountInNorthDirection;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public String getIntervalStartTime() {
        return intervalStartTime;
    }

    public String getIntervalEndTime() {
        return intervalEndTime;
    }

    @Override
    public String toString() {
        return  "\n Analysis for '" + getRowDescription() + " "
                +getRowCount()+'\'' +
                ", Start Time='" + getIntervalStartTime() + '\'' +
                ", End Time='" + getIntervalEndTime() + '\'' +
                ", Count In South Direction=" +
                getVehicleCountInSouthDirection() +
                ", Count In North Direction=" +
                getVehicleCountInNorthDirection() +
                ", totalCount=" + getTotalCount() ;

    }

}
