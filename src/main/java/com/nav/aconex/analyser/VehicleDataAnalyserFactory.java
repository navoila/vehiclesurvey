package com.nav.aconex.analyser;

import com.nav.aconex.analyser.result.VehicleDataAnalysisResult;

import java.util.HashMap;
import java.util.Map;

public class VehicleDataAnalyserFactory {

    final static Map<VehicleDataAnalyserType, VehicleDataAnalyser> map = new HashMap<>();
    static {
        map.put(VehicleDataAnalyserType.VEHICLE_COUNT_PER_15_MINUTE,
                new VehicleCountPer15MinuteAnalyser());
        map.put(VehicleDataAnalyserType.VEHICLE_COUNT_PER_20_MINUTE,
                new VehicleCountPer20MinuteAnalyser());
        map.put(VehicleDataAnalyserType.VEHICLE_COUNT_PER_HALF_AN_HOUR,
                new VehicleCountPerHalfAnHourAnalyser());
        map.put(VehicleDataAnalyserType.VEHICLE_COUNT_PER_HOUR,
                new VehicleCountPerHourAnalyser());
        map.put(VehicleDataAnalyserType.VEHICLE_COUNT_PER_DAY,
                new VehicleCountPerDayAnalyser());
    }
    public VehicleDataAnalyser getAnalyser(VehicleDataAnalyserType vehicleDataAnalyser){
        VehicleDataAnalyser analyser = map.get(vehicleDataAnalyser);
        if(analyser != null) {
            return analyser;
        }
        throw new IllegalArgumentException("Analyser yet to be implemented " );
    }
}
