package com.nav.aconex.parser;

import com.nav.aconex.constants.Direction;
import com.nav.aconex.constants.VehicleSurveyConfigConstants;
import com.nav.aconex.exception.InvalidVehicleDataException;
import com.nav.aconex.model.VehicleDetails;
import com.nav.aconex.io.reader.VehicleDataFileReader;

import java.util.ArrayList;
import java.util.List;

public class VehicleDataParser {

    private int currentDay;
    private int lastEntryTimeInMilliSecs;
    private VehicleDataFileReader vehicleDataFileReader;

    public VehicleDataParser(VehicleDataFileReader vehicleDataFileReader) {
        if(vehicleDataFileReader==null)
            throw new IllegalArgumentException("VehicleDataFileReader is " +
                    "mandatory");
        this.vehicleDataFileReader = vehicleDataFileReader;
    }

    public List<VehicleDetails> parse() throws InvalidVehicleDataException {
        List<String> rawInputData = vehicleDataFileReader.readData();
        List<VehicleDetails> processedVehicleEntries = new ArrayList<>();
        currentDay = 1;

        while (!rawInputData.isEmpty() && !(rawInputData.size()<2)){
            Direction direction = findDirection(rawInputData);
            if (direction == Direction.SOUTH)
                // sensor A rear axle = third entry considering south direction
                rawInputData = parseEachVehicleDataMovingInADirection(rawInputData,
                        processedVehicleEntries, rawInputData.get(0), rawInputData
                                .get(2), Direction.SOUTH );
            else
                rawInputData = parseEachVehicleDataMovingInADirection(rawInputData,
                        processedVehicleEntries, rawInputData.get(0), rawInputData
                                .get(1), Direction.NORTH );
        }
        return processedVehicleEntries;
    }
    //TODO:rename this method to be more meaningful
    private List<String> parseEachVehicleDataMovingInADirection(List<String> rawInputData, List<VehicleDetails> processedVehicleDetailEntries,
                                                                final String frontAxleInputEntry, final String rearAxleInputEntry,
                                                                final
                                                                Direction
                                                                        direction ) throws InvalidVehicleDataException {
        int frontAxleTimeInMilliSecs = VehicleDataUtil.parseMilliSecondsFromMidnight(frontAxleInputEntry);
        int rearAxleTimeInMilliSecs = VehicleDataUtil.parseMilliSecondsFromMidnight(rearAxleInputEntry);
        int speed = VehicleDataUtil.calculateSpeedinKph(frontAxleTimeInMilliSecs,rearAxleTimeInMilliSecs);
        if (rearAxleTimeInMilliSecs<=frontAxleTimeInMilliSecs)
            throw new InvalidVehicleDataException("Invalid entry at : " +
                    frontAxleInputEntry);
        if(isItNextDay(frontAxleTimeInMilliSecs)){
            currentDay++;
        }
        VehicleDetails entry = new VehicleDetails(processedVehicleDetailEntries.size()+1, direction,
                frontAxleTimeInMilliSecs, rearAxleTimeInMilliSecs, speed, currentDay);
        lastEntryTimeInMilliSecs = frontAxleTimeInMilliSecs;
        processedVehicleDetailEntries.add(entry);
        //System.out.println(entry.toString());
        int processedEntriesToRemoveFromInputData = Direction
                .SOUTH==direction?4:2;
        return rawInputData.subList(processedEntriesToRemoveFromInputData,rawInputData.size());
    }


    private boolean isItNextDay(int currentEntryTimeInMilliSecs) {
        if (currentEntryTimeInMilliSecs <  lastEntryTimeInMilliSecs) {
            return true;
        }
        return false;
    }

    private Direction findDirection(List<String> inputData)
            throws InvalidVehicleDataException{
        if (inputData.size()>=2 && inputData.get(0).startsWith(VehicleSurveyConfigConstants.SENSOR1_DATA_ITEM_PREFIX)
                && inputData.get(1).startsWith(VehicleSurveyConfigConstants.SENSOR1_DATA_ITEM_PREFIX)) {
            return Direction.NORTH;
        }
        if(inputData.size()>3 && inputData.get(0).startsWith(VehicleSurveyConfigConstants.SENSOR1_DATA_ITEM_PREFIX)
                && inputData.get(1).startsWith(VehicleSurveyConfigConstants.SENSOR2_DATA_ITEM_PREFIX)
                && inputData.get(2).startsWith(VehicleSurveyConfigConstants.SENSOR1_DATA_ITEM_PREFIX)
                && inputData.get(3).startsWith(VehicleSurveyConfigConstants.SENSOR2_DATA_ITEM_PREFIX)) {
            return Direction.SOUTH;
        }
        throw new InvalidVehicleDataException("Invalid order of entries starting at : " +
                inputData.get(0));
    }
}

