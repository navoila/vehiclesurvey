package com.nav.aconex.parser;

import com.nav.aconex.exception.InvalidVehicleDataException;

import java.math.BigDecimal;

public class VehicleDataUtil {
    public static final double DISTANCE_BETWEEN_AXLES_IN_METRES = 2.5;
    public static final int MAX_MILLISECONDS_IN_A_DAY = 86400000;

    public static int parseMilliSecondsFromMidnight(String rawInputDataEntry) throws InvalidVehicleDataException {
        try {
            int milliSeconds = Integer.parseInt(rawInputDataEntry.substring(1));
            if (milliSeconds < 0 || milliSeconds> MAX_MILLISECONDS_IN_A_DAY)
                throw new InvalidVehicleDataException("Invalid entry at : "+
                        rawInputDataEntry);
            return milliSeconds;
        }catch (NumberFormatException e){
            throw new InvalidVehicleDataException("Invalid entry at : "+
                    rawInputDataEntry, e);
        }

    }

    public static double round(double value, int numberOfDigitsAfterDecimalPoint) {
        BigDecimal bigDecimal = new BigDecimal(value);
        bigDecimal = bigDecimal.setScale(numberOfDigitsAfterDecimalPoint,
                BigDecimal.ROUND_HALF_UP);
        return bigDecimal.doubleValue();
    }

    public static int calculateSpeedinKph(int frontAxleTimeInMilliSecs, int
            rearAxleTimeInMilliSecs) {
        if((rearAxleTimeInMilliSecs-frontAxleTimeInMilliSecs)<=0){
            throw new IllegalArgumentException("Please pass valid values");
        }
        //Speed = distance \ time;
        double speedInMetresPerMilliSecs = (DISTANCE_BETWEEN_AXLES_IN_METRES) /
                (rearAxleTimeInMilliSecs - frontAxleTimeInMilliSecs);
        double speedInMetresPerSecs = round(speedInMetresPerMilliSecs * 1000,
                4);
        //Conversion: KmpH = MpSec times 3.6 See Reference -
        // http://m.measurementconversions.org/speeds/metrespersecondtokilometresperhour.htm
        double speedInKmPerHour = speedInMetresPerSecs * 3.6;
        return (int) round(speedInKmPerHour, 2);
    }
}
