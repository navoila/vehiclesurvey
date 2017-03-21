package com.nav.aconex.analyser;

import com.nav.aconex.analyser.result.IndividualIntervalAnalysisResult;
import com.nav.aconex.analyser.result.VehicleDataAnalysisResult;
import com.nav.aconex.constants.Direction;
import com.nav.aconex.model.VehicleDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class VehicleCountPerTimeIntervalAnalyser implements VehicleDataAnalyser {

    public static int MINUTES_IN_A_DAY = 60 * 24;
    public static int MILLISECONDS_IN_A_MINUTE = 60 * 1000;

    private String analysisDescription;
    private int intervalInMinutes;
    private int peakTimeCountInInterval;
    private String peakVolumeIntervalStartTime;

    public VehicleCountPerTimeIntervalAnalyser(int intervalInMinutes,
                                               String analysisDescription) {
        if(intervalInMinutes==0 || MINUTES_IN_A_DAY % intervalInMinutes !=0 ){
            throw new IllegalArgumentException("Invalid interval minutes " +
                    "value passed for parameter intervalInMinutes");
        }
        this.intervalInMinutes = intervalInMinutes;
        this.analysisDescription = analysisDescription;

    }

    @Override
    public VehicleDataAnalysisResult analyse(List<VehicleDetails> vehicleDetails) {
        if(vehicleDetails == null || vehicleDetails.isEmpty()){
            throw new IllegalArgumentException("VehicleDetails with contents " +
                    "is mandatory");
        }

        int numberOfIntervals = MINUTES_IN_A_DAY / intervalInMinutes;

        List<IndividualIntervalAnalysisResult> individualIntervalResults = new ArrayList<>();
        for (int interval = 1; interval <= numberOfIntervals; interval++) {
            int startTimeInMilliSeconds = ((interval-1) * intervalInMinutes * MILLISECONDS_IN_A_MINUTE);
            int endTimeInMilliSeconds = (interval * intervalInMinutes * MILLISECONDS_IN_A_MINUTE);
            int vehicleCountInSouthDirection = countEntriesInTheInterval(vehicleDetails,
                    startTimeInMilliSeconds,endTimeInMilliSeconds, Direction.SOUTH);
            int vehicleCountInNorthDirection = countEntriesInTheInterval(vehicleDetails,
                    startTimeInMilliSeconds,endTimeInMilliSeconds, Direction.NORTH);
            int total = vehicleCountInSouthDirection + vehicleCountInNorthDirection;
            String intervalStartTimeFormatted = formatTimeToHHmmss(startTimeInMilliSeconds);
            String intervalEndTimeFormatted = formatTimeToHHmmss(endTimeInMilliSeconds);
            IndividualIntervalAnalysisResult individualResult = new
                    IndividualIntervalAnalysisResult("Interval", interval,
                    vehicleCountInSouthDirection,
                    vehicleCountInNorthDirection,total,
                    intervalStartTimeFormatted, intervalEndTimeFormatted);
            individualIntervalResults.add(individualResult);
            if(total > peakTimeCountInInterval) {
                peakTimeCountInInterval = total;
                peakVolumeIntervalStartTime = intervalStartTimeFormatted ;
            }

        }

        VehicleDataAnalysisResult result = new VehicleDataAnalysisResult
                (analysisDescription, individualIntervalResults, peakTimeCountInInterval,
                        peakVolumeIntervalStartTime);
        return result;
    }

    private String formatTimeToHHmmss(int timeInMillis){
        LocalDateTime localDateTime = LocalDate.now().atStartOfDay();
        localDateTime = localDateTime.plusSeconds(timeInMillis/1000);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "HH:mm:ss" );
        return localDateTime.format(formatter);

    }
    private int countEntriesInTheInterval(List<VehicleDetails>
                                                  vehicleDetails, int
            intervalStartTimeInMillis, int intervalEndTimeInMillis, Direction direction) {

        return vehicleDetails.stream().filter(hasVehicleEnteredInTheInterval
                (intervalStartTimeInMillis,intervalEndTimeInMillis,direction))
                .collect(Collectors.toList())
                .size();
    }


    private static Predicate<VehicleDetails> hasVehicleEnteredInTheInterval
            (int intervalStartTimeInMillis, int intervalEndTimeInMillis,
             Direction direction) {
        return vehicle -> vehicle
                .getFrontAxleMilliSecondsAfterMidnight() < intervalEndTimeInMillis &&
                vehicle.getFrontAxleMilliSecondsAfterMidnight() >= intervalStartTimeInMillis &&
                vehicle.getDirection() == direction;
    }

}
